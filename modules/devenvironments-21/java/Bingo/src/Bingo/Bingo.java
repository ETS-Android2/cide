package Bingo;

import java.util.ArrayList;

public class Bingo {

    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 99;
    private final int PLAYER_LETTERS = 10;

    private Machine maquina;
    private Player[] players;
    private int playerNumber;

    public Bingo(int playerNumber){
        this.maquina = new Machine(1,99);
        this.playerNumber = playerNumber;
        this.players = generatePlayers();
    }

    public void startGame(){
        int counter = 0;
        while (!maquina.isEmpty()){
            int number = maquina.getNumber();
            this.players[0].removeValue(number);
            this.players[1].removeValue(number);
            if(this.players[0].isEmpty() || this.players[1].isEmpty()){
                break;
            }
        }
    }

    public Player[] getPlayers(){
        return this.players;
    }

    private Player[] generatePlayers(){
        Player[] output = new Player[this.playerNumber];
        for (int i = 0; i < output.length; i++) {
            output[i] = new Player(this.PLAYER_LETTERS,this.MAX_NUMBER);
        }
        return output;
    }

}
