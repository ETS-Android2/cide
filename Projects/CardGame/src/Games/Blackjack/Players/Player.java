package Games.Blackjack.Players;

/*

    Project     Programming21
    Package     Games.Blackjack.Players    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    
*/

import Games.Blackjack.Blackjack;
import Games.Blackjack.Deck.Card.Card;
import Games.Blackjack.Deck.Card.Face;
import Games.Blackjack.Deck.Card.Suit;
import Games.Blackjack.Deck.Deck;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */


public abstract class Player extends Blackjack {

    /*
     * ATTRIBUTES
     * */

    public  String playerName;
    private ArrayList<Card> playerCards;

    private int currentHand;
    private boolean busted;
    private boolean canGetCards;
    private boolean winner;
    private float bet;

    // TEST
    private boolean hasAce;

    /*
     * METHODS
     * */

    public Player(){
        playerCards = new ArrayList<Card>();
    }

    // Setters

    public void setBet(float betToSet){
        this.bet = betToSet;
    }

    // Updates

    private void checkCardValues(){
        for (Card playerCard : this.playerCards) {
            if(playerCard.getCardFace() == Face.ACE.getFaceValue()){
                if(this.currentHand < 10){
                    updateHand(10);
                } else {
                    updateHand(1);
                }
            } else {
                updateHand(playerCard.getCardFace());
            }
        }
    }
    
    public void getCardFromDeck(){
        // This is the function for obtain next cards and check its values.
        playerCards.add(gameDeck.obtainNextCard());
        checkCardValues();
    }

    public void getCardFromDeck(int numberOfCardsToObtain){
        for (int i = 1; i <= numberOfCardsToObtain ; i++) {
            this.playerCards.add(gameDeck.obtainNextCard());
        }
        checkCardValues();
    }

    private void updateHand(int nextCard){
        this.currentHand += nextCard;
    }
    
    private void toBusted(){
        this.busted = true;
    }

    private void winsRound(){
        this.winner = true;
    }

    private void stillDeck(){
        this.canGetCards = false;
    }

    public void doubleBet(){
        this.bet *= 2;
    }

    // Getters

    public int printCurrentHand(){
        return this.currentHand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerCards=" + this.playerCards +
                "playerName=" + this.playerName +
                "currentHand=" + this.currentHand +
                '}';
    }

    // TESTS
    /*
    public void test_addCard(Card newCard){
        this.playerCards.add(newCard);
    }

    public void test_checkCardValues() {

        boolean isAce = false;
        int nextCard = 1;
        boolean hasNextCard = false;

        for (Card playerCard : this.playerCards) {
            if (playerCard.getCardFace() == Face.ACE.getFaceValue()) {
                isAce = true;
            }
            if(!hasNextCard && isAce){

                if(isAce && this.currentHand < 10){
                    updateHand(11);
                } else if(isAce && this.currentHand > 10){
                    updateHand(1);
                }
                hasNextCard = false;
            } else if(hasNextCard){



                nextCard++;
                if(nextCard == this.playerCards.size()){
                    hasNextCard = false;
                }
            }



            if (isAce && this.currentHand < 10) {
                updateHand(11);
                hasAce = false;
            } else if (isAce && this.currentHand > 10) {
                updateHand(1);
                hasAce = false;
            } else if (!hasAce && ) {
                updateHand(playerCard.getCardFace());
            } else {
                test_checkCardValues();
            }

        }
    }

     */
}
