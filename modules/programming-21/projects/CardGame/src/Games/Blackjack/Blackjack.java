package Games.Blackjack;

/*

    Project     Programming21
    Package     Games.Blackjack    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    Blackjack is a card game based on two or more players have to
    take cards and the ones with the highest deck without busting
    wins the round.
    
*/

// Parent
import Games.Blackjack.Players.Client.Client;
import Games.Blackjack.Players.Croupier.Croupier;
import Games.Game;

// Internal objects
import Games.Blackjack.Deck.Deck;
import Games.Blackjack.Players.Player;

// Utilities
import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class Blackjack extends Game {

    /*
     * ATTRIBUTES
     * */

    final public static String GAME_NAME = "BlackJack";
    final public static String GAME_VERSION = "1.0";
    final public static String GAME_STAGE = "DEVELOPMENT";
    final public static String GAME_AUTHOR = "(c) Carlos Pomares";

    private int numberOfRounds;
    private Player lastRoundWinner;
    public boolean tie;

    private String clientName;
    private double minimumBet = 50; // By default, minimum bet is 50
    private double maximumBet = 500; // By default, maximum bet is 500
    private int numberOfClients = 1; // By default, number of clients in the game is 1

    // Deck
    public static Deck gameDeck;

    // Players, at version 1.0
    // 1 Croupier, 1 Client.
    private Croupier croupier;
    private Player client;

    /*
     * METHODS
     * */

    // Constructor
    public Blackjack(){
        // FUTURE FEATURES
        /*
            MAXIMUN BET
            PLAYERS
            ¿¿NETWORK??
         */

        // Initialize some objects.
        gameDeck = new Deck();

    }

    public Blackjack(double minBet, double maxBet){

        // Set minimum and maximum bet if exists.
        this.minimumBet = minBet;
        this.maximumBet = maxBet;

        // Initialize some objects.
        gameDeck = new Deck();

    }

    // For future multiuser functionality.
    private void addClient(String clientName){
        this.client = new Client(clientName);
    }
    private void startRound(Scanner userIn){
        // Shuffle deck.
        gameDeck.shuffleCards();

        boolean roundRuntime = false;
        // One Round is a loop, when one of the players wins round this loops ends.
        while(!roundRuntime){
            try {

                // Croupier gets two cards.
                // Clients only can see the second card of the crupier.
                // crupier.getCardFromDeck(2);
                System.out.printf("%s gets cards.\n", this.croupier.playerName);
                croupier.getCard(2);
                System.out.println("CARDS: [ Hidden ] " + this.croupier.getPlayerCard(1));

                // Client gets two topCards cards.
                // client.getCardFromDeck(2);
                System.out.printf("%s gets cards.\n", this.client.playerName);
                client.getCard(2);
                System.out.println(this.client.toString());

                boolean exit = false;

                // Client only can decide obtain new card if is not busted or double his bet and get only one card.
                do {

                    System.out.printf("%s, stay or want one more card?: \n", this.client.playerName);

                    try {

                        String userOrder = userIn.nextLine();

                        if(!this.client.isBusted()){
                            switch (userOrder.toLowerCase()){
                                case "yes", "still", "wait", "stop" -> {
                                    this.client.stillsDeck();
                                    exit = true;
                                }
                                case "no", "1", "get", "get one more card", "card", "one more", "one", "take", "want" -> {
                                    this.client.getCard();
                                    System.out.println(this.client.toString());
                                }
                                default -> {
                                    System.out.println("Try again!");
                                }
                            }
                        } else {
                            exit = true;
                            System.out.printf("\nBusted, with hand value: %d", this.client.getCurrentHand());
                        }

                    } catch (Exception runtimeError){runtimeError.printStackTrace();}
                } while(!exit);

                // Satisfy croupier
                System.out.println("\nCroupier is under 17...");
                this.croupier.crupierNotSatisfied();
                System.out.printf("\nCroupier last hand, %s",this.croupier.toString());

                // Get Winner
                this.lastRoundWinner = Player.getWinner(client,croupier);

                roundRuntime = true;

            } catch (Exception runtimeError){runtimeError.printStackTrace();}
        }
    }

    // Menus
    private void mainMenu(Scanner userIn){

        boolean exitOrder = false;

        while(!exitOrder){

            System.out.println("\n---- Blackjack Game ----");
            System.out.printf("------ By %s ------ \n\n", Blackjack.GAME_AUTHOR);

            System.out.print("Client name: ");
            this.clientName = userIn.nextLine();

            // Create croupier object
            croupier = new Croupier();

            // Create client object
            addClient(this.clientName);

            System.out.printf("\n---- Hello %s, today your Croupier is %s ----\n", this.clientName, this.croupier.playerName);
            System.out.println("1: Start new game");
            System.out.println("2: Exit\n");

            System.out.print("Order: ");

            try {
                String userOrder = userIn.nextLine();

                switch (userOrder.toLowerCase()){
                    case "start", "start new game", "1", "go", "play" -> {
                        startRound(userIn);
                        System.out.println("\nWinner: " + this.lastRoundWinner.playerName);
                    }
                    case "exit", "2", "exit application", "exit blackjack" -> {
                        exitOrder = true;
                    }
                }
            } catch (Exception error) { error.printStackTrace();}
        }
    }

    @Override
    public void runGame() {
        mainMenu(new Scanner(System.in));
    }
}
