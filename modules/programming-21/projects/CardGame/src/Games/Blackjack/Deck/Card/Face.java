package Games.Blackjack.Deck.Card;

/*

    Project     Programming21
    Package     Games.Blackjack.Deck.Card    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


public enum Face {
    ACE(1),DEUCE(2),
    THREE(3),FOUR(4),
    FIVE(5),SIX(6),
    SEVEN(7),EIGHT(8),
    NINE(9), TEN(10),
    JACK(10),QUEEN(10),
    KING(10);

    private final int cardFace;

    private Face(int cardFace){
        this.cardFace = cardFace;
    }

    public int getFaceValue(){
        return this.cardFace;
    }
}
