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

    private static Deck gameDeck;
    private static ArrayList<Player> players;

    /*
     * METHODS
     * */

    // Constructor
    public Blackjack(){

    }

    @Override
    public void runGame() {

    }
}
