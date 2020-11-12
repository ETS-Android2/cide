package Games.Blackjack.Players.Cuprier;

/*

    Project     Programming21
    Package     Games.Blackjack.Players.Cuprier    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    
*/

import Games.Blackjack.Deck.Card.Card;
import Games.Blackjack.Players.Player;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */


public class Crupier extends Player {

    /*
     * ATTRIBUTES
     * */

    /*
     * METHODS
     * */

    // Constructor
    public Crupier(){
        super();
        this.playerName = "Crupier";
    }

    public void crupierNotSatisfied(){
        while(this.getCurrentHand() <= 17){
            this.getCardFromDeck();
        }
    }

}
