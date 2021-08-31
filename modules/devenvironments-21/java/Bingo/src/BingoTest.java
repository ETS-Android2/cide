import Bingo.Bingo;
import Bingo.Player;

import java.util.ArrayList;

public class BingoTest {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        Bingo bingo = new Bingo(2);
        bingo.startGame();
        long stopTime = System.nanoTime();

        showLetter(bingo.getPlayers()[0].getLetter());
        System.out.println("\n");
        showLetter(bingo.getPlayers()[1].getLetter());
        System.out.println("\n");

        System.out.println(
                (bingo.getPlayers()[0].isEmpty() && bingo.getPlayers()[1].isEmpty()) ? "Empate"
                        : (bingo.getPlayers()[0].isEmpty()) ? "Player 0 GANA"
                        : (bingo.getPlayers()[1].isEmpty()) ? "Player 1 GANA" : null);

        System.out.println("\nTime to resolve: " + ((double)(stopTime - startTime) / 1000000));

    }

    public static void showLetter(ArrayList<Integer> letter){
        for(Integer x : letter){
            System.out.print(x + " ");
        }
    }

}
