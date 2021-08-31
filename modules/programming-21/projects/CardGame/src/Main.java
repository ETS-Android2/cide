/*

    Project     Programming21

    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    
*/

import Games.Game;
import Games.Blackjack.Blackjack;

/**
 * @author Carlos Pomares
 */

public class Main {

    // Games
    public static Game blackjack;

    public static void main(String[] args) {
        blackjack = new Blackjack();
        blackjack.runGame();
    }
}
