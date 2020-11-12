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
import Games.Blackjack.Deck.Card.Card;
import Games.Blackjack.Deck.Card.Face;
import Games.Blackjack.Deck.Card.Suit;
import Games.Blackjack.Players.Client.Client;
import Games.Blackjack.Players.Cuprier.Crupier;
import Games.Game;

// Internal objects
import Games.Blackjack.Deck.Deck;
import Games.Blackjack.Players.Player;

// Utilities
import java.util.ArrayList;

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

    private double minimumBet = 50; // By default, minimum bet is 50
    private double maximumBet = 500; // By default, maximum bet is 500
    private int numberOfClients = 1; // By default, number of clients in the game is 1

    // Deck
    protected Deck gameDeck;

    // Players, at version 1.0
    // 1 Crupier, 1 Client.
    Player crupier;
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

        // Crupier gets two cards.
        // Clients only can see the second card of the crupier.
        // crupier.getCardFromDeck(2);
        crupier.toString();

        // Client gets two topCards cards.
        // client.getCardFromDeck(2);
        client.toString();

        // Client only can decide obtain new card if is not busted or double his bet and get only one card.


        // If clients stay with his deck, client and crupier hands are showed.

    }

    @Override
    public void runGame() {

        /*
        System.out.println("Hello World!");

        this.crupier = new Crupier();
        addClient("Carlos");

        // TESTING

        Card ACE = new Card(Suit.CLUBS, Face.ACE);
        Card THREE = new Card(Suit.DIAMONDS,Face.THREE);
        Card SIX = new Card(Suit.HEARTS,Face.SIX);
        Card FIVE = new Card(Suit.SPADES,Face.FIVE);

        // ADD CARD TO CARLOS
        client.test_addCard(SIX);
        client.test_addCard(ACE);
        client.test_addCard(FIVE);
        client.test_checkCardValues();

        System.out.println(client.toString());
         */

    }
}
