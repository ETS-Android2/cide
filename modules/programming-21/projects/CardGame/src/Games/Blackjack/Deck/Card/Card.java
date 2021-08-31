package Games.Blackjack.Deck.Card;

/*

    Project     Programming21
    Package     Games.Blackjack.Deck.Card    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    
*/

import java.util.Random;

/**
 * @author Carlos Pomares
 */

public class Card {

    // https://blog.udemy.com/blackjack-card-values/

    /*
     * ATTRIBUTES
     * */

    private Face cardFace;
    private Suit cardSuit;

    /*
     * METHODS
     * */

    // Constructor
    public Card(Suit cardSuit, Face cardFace){
        this.cardSuit = cardSuit;
        this.cardFace = cardFace;
    }

    // Getters
    public Face getCardFace() {
        return cardFace;
    }

    public int getCardFaceValue() {
        return cardFace.getFaceValue();
    }

    public String getCardSuit() {
        return cardSuit.getSuitValue();
    }

    @Override
    public String toString() {
        return "[SUIT: " + getCardSuit() +
                ", FACE: " + getCardFace() +
                "]";
    }
}
