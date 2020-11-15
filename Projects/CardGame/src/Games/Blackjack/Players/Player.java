package Games.Blackjack.Players;

/*

    Project     Programming21
    Package     Games.Blackjack.Players    

    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION

*/

import Games.Blackjack.Blackjack;
import Games.Blackjack.Deck.Card.Card;
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

    // Croupier specific
    protected ArrayList<Card> indPlayerCards = this.playerCards;

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

    // Static procedures
    public static Player getWinner(Player firstPlayer, Player secondPlayer){

        Player winner = null;

        if(firstPlayer.isBusted() == false && secondPlayer.isBusted() == false){
            if(firstPlayer.getCurrentHand() > secondPlayer.getCurrentHand()){
                firstPlayer.setWinner();
                winner = firstPlayer;
            } else if(firstPlayer.getCurrentHand() < secondPlayer.getCurrentHand()){
                secondPlayer.setWinner();
                winner = secondPlayer;
            } else if(firstPlayer.getCurrentHand() == secondPlayer.getCurrentHand()) {
                winner = null;
            }
        } else if(firstPlayer.isBusted()){
            secondPlayer.setWinner();
            winner = secondPlayer;
        } else if(secondPlayer.isBusted()) {
            firstPlayer.setWinner();
            winner = firstPlayer;
        } else {
            winner = null;
        }

        return winner;
    }

    // Setters
    public void setBet(float betToBeSet){
        this.bet = betToBeSet;
    }
    public void setBusted(){
        this.busted = true;
    }
    private void setWinner(){
        this.winner = true;
    }

    // Getters
    public int getCurrentHand(){return this.currentHand;}
    public boolean isBusted(){return this.busted;}
    public boolean isWinner(){return this.winner;}
    public boolean canGetCards(){return this.canGetCards;}
    public void getCard(){
        // This is the function for obtain next cards and check its values.
        if(this.canGetCards){ playerCards.add(gameDeck.obtainNextCard());
            updateHandCards(); }
    }
    public void getCard(int numberOfCardsToObtain){
        for (int i = 1; i <= numberOfCardsToObtain ; i++) {
            this.playerCards.add(gameDeck.obtainNextCard());
        }
        updateHandCards();
    }
    public Card getPlayerCard(int card){return this.playerCards.get(card);}

    // Updates
    private void updateHandCards() {

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
            this.setBusted();
            this.canGetCards = false;
        }
    }
    public void stillsDeck(){
        this.canGetCards = false;
    }
    public void doubleBet(){
        this.bet *= 2;
    }

    @Override
    public String toString() {
        return "CARDS: " + this.playerCards +
                ", HAND VALUE: " + this.getCurrentHand();
    }
}
