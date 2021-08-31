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

public enum Suit {
    HEARTS("Hearts"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    SPADES("Spades");

    private final String cardSuit;

    private Suit(String cardSuit){
        this.cardSuit = cardSuit;
    }

    public String getSuitValue() {
        return this.cardSuit;
    }
}
