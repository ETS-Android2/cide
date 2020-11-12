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

    private double minimumBet = 50; // By default, minimum bet is 50
    private double maximumBet = 500; // By default, maximum bet is 500
    private int numberOfClients = 1; // By default, number of clients in the game is 1

    // Deck
    public static Deck gameDeck;

    // Players, at version 1.0
    // 1 Croupier, 1 Client.
    Croupier crupier;
    Player client;

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

        // Add static players

    }

    public Blackjack(double minBet, double maxBet){

        // Set minimum and maximum bet if exists.
        this.minimumBet = minBet;
        this.maximumBet = maxBet;

        // Initialize some objects.
        gameDeck = new Deck();

    }

    private void addClient(String clientName){
        this.client = new Client(clientName);
    }

    private void startRound(){

        // Shuffle deck.
        gameDeck.shuffleCards();

        boolean exit = false;

        // One Round is a loop, when one of the players wins round this loops ends.
        while(!exit){

            // Croupier gets two cards.
            // Clients only can see the second card of the crupier.
            // crupier.getCardFromDeck(2);
            crupier.initialDeck(2);

            // Client gets two topCards cards.
            // client.getCardFromDeck(2);
            client.initialDeck(2);

            // Client only can decide obtain new card if is not busted or double his bet and get only one card.
            do{

                client.getCardFromDeck();

            } while (client.canGetCards());

            crupier.crupierNotSatisfied();

            /*
            // If clients stay with his deck, client and crupier hands are showed.
            if(Player.getWinner(client,crupier) == null){
                this.tie = true;
            } else {
                lastRoundWinner = Player.getWinner(client,crupier);
            }
             */

            System.out.println(client.toString());
            System.out.println(crupier.toString());

            lastRoundWinner = Player.getWinner(client,crupier);
            exit = true;

        }
    }

    @Override
    public void runGame() {

        // Once the game is running, client must be created with his name;
        addClient("Carlos");
        crupier = new Croupier();

        startRound();

        System.out.println(this.lastRoundWinner.toString());

    }
}
