package Bingo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    private ArrayList<Integer> letter;
    private int maxLetterNumber;
    private int maxNumberInLetter;

    public Player(int maxLetterNumbers, int maxNumber){
        this.maxLetterNumber = maxLetterNumbers;
        this.maxNumberInLetter = maxNumber;
        this.letter = generateLetter();
    }

    public void removeValue(int value){
        this.letter.remove((Integer) value);
    }

    public boolean isEmpty(){
        return this.letter.size() == 0;
    }

    public ArrayList<Integer> getLetter() {
        return letter;
    }

    private ArrayList<Integer> generateLetter(){
        ArrayList<Integer> letter = new ArrayList<Integer>();
        for(int index = 0; index < this.maxLetterNumber; index++){
            int randomNumber = (int)(Math.random() * this.maxNumberInLetter + 1);
            while(letter.contains((Integer) randomNumber)){
                randomNumber = (int)(Math.random() * this.maxNumberInLetter + 1);
            }
            letter.add(randomNumber);
        }
        return letter;
    }

}
