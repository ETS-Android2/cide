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
import Games.Blackjack.Deck.Deck;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */


public abstract class Player extends Blackjack {

    /*
     * ATTRIBUTES
     * */

    // Player specific
    public  String playerName;
    private ArrayList<Card> playerCards;
    private int currentHand;
    private float bet;

    // Player states
    private boolean busted;
    private boolean canGetCards = true;
    private boolean winner;

    /*
     * METHODS
     * */

    // Constructor
    public Player(){
        playerCards = new ArrayList<Card>();
    }

    // Setters
    public void setBet(float betToSet){
        this.bet = betToSet;
    }

    // Updates
    private void addCardValues() {

        this.currentHand = 0;
        boolean hasAce = false;
        boolean hasSpecialCards = false;

        for (Card playerCard : this.playerCards) {
            switch (playerCard.getCardFace()){
                case ACE -> {
                    hasAce = true;
                }
                case JACK, QUEEN, KING -> {
                    hasSpecialCards = true;
                }
            }
            this.currentHand += playerCard.getCardFaceValue();
        }
        if(hasAce){
            if((this.currentHand - 1) <= 10 && !hasSpecialCards){
                this.currentHand += (10 - 1);
            } else if(hasSpecialCards){
                this.currentHand += 10;
            }
        }
        if(this.currentHand > 21){
            this.toBusted();
            this.canGetCards = false;
        }
    }
    
    public void getCardFromDeck(){
        // This is the function for obtain next cards and check its values.
        if(this.canGetCards){ playerCards.add(gameDeck.obtainNextCard());addCardValues(); }
    }

    public void initialDeck(int numberOfCardsToObtain){
        for (int i = 1; i <= numberOfCardsToObtain ; i++) {
            this.playerCards.add(gameDeck.obtainNextCard());
        }
        addCardValues();
    }

    private void toBusted(){
        this.busted = true;
    }

    private void toWinner(){
        this.winner = true;
    }

    private void stillDeck(){
        this.canGetCards = false;
    }

    public void doubleBet(){
        this.bet *= 2;
    }

    // State changers

    public static Player getWinner(Player firstPlayer, Player secondPlayer){

        Player winner = null;

        if(firstPlayer.isBusted() == false && secondPlayer.isBusted() == false){
            if(firstPlayer.getCurrentHand() > secondPlayer.getCurrentHand()){
                firstPlayer.toWinner();
                winner = firstPlayer;
            } else if(firstPlayer.getCurrentHand() < secondPlayer.getCurrentHand()){
                secondPlayer.toWinner();
                winner = secondPlayer;
            } else if(firstPlayer.getCurrentHand() == secondPlayer.getCurrentHand()) {
                winner = null;
            }
        } else if(firstPlayer.isBusted()){
            secondPlayer.toWinner();
            winner = secondPlayer;
        } else if(secondPlayer.isBusted()) {
            firstPlayer.toWinner();
            winner = firstPlayer;
        } else {
            winner = null;
        }

        return winner;
    }

    // Getters

    public int printCurrentHand(){return this.currentHand;}
    public int getCurrentHand(){return this.currentHand;}
    public boolean isBusted(){return this.busted;}
    public boolean isWinner(){return this.winner;}
    public boolean canGetCards(){return this.canGetCards;}

    @Override
    public String toString() {
        return "PLAYER: " + this.playerName + "\n" +
                "CARDS: " + playerCards.toString() + "\n" +
                "HAND VALUE: " + this.currentHand + "\n" +
                "BUSTED: " + this.isBusted() + "\n" +
                "WINNER: " + this.isWinner() + "\n";
    }
}
