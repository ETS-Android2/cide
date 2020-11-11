package Games.Blackjack.Players;

/*

    Project     Programming21
    Package     Games.Blackjack.Players    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-11

    DESCRIPTION
    
*/

import Games.Blackjack.Deck.Card.Card;
import Games.Blackjack.Deck.Deck;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */


public abstract class Player {

    /*
     * ATTRIBUTES
     * */

    public  String playerName;
    private ArrayList<Card> playerCards;

    private boolean busted;
    private boolean canGetCards;
    private boolean winner;
    private float bet;

    /*
     * METHODS
     * */

    // Setters

    public void setBet(float betToSet){
        this.bet = betToSet;
    }

    // Updates

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

    public void getRandomCardFromDeck(Deck gameDeck){
        // this.playerCards.add(gameDeck);
        // TODO Enable to add cards to the deck of the player.
    }

    public boolean isBusted(){
        return this.busted;
    }

    public boolean stillsDeck(){
        return this.canGetCards;
    }

    public ArrayList<Card> currentCards(){
        return playerCards;
    }

}
