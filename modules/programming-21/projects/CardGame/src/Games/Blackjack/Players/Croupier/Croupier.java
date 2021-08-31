package Games.Blackjack.Players.Croupier;

/*

    Project     Programming21
    Package     Games.Blackjack.Players.Cuprier    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    
*/

import Games.Blackjack.Players.Player;

/**
 * @author Carlos Pomares
 */


public class Croupier extends Player {

    /*
     * ATTRIBUTES
     * */

    /*
     * METHODS
     * */

    // Constructor
    public Croupier(){
        super();
        this.playerName = "James, the Croupier";
    }

    // Update
    public void crupierNotSatisfied(){
        while(this.getCurrentHand() <= 17){
            this.getCard();
        }
    }

}
