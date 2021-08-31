package Bingo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Machine {

    private HashMap<Integer,Boolean> numbers;

    private int minNumber;
    private int maxNumber;

    public Machine(int minNumber, int maxNumber){
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.numbers = generateNumbers();
    }

    public int getNumber(){
        int randomNumber = (int)(Math.random() * 99 + 1);
        if(!this.numbers.get(randomNumber)){
            this.numbers.replace(randomNumber,true);
            return randomNumber;
        } else {
            return getNumber();
        }
    }

    public boolean isEmpty(){
        int trueCounter = 0;
        for(Boolean value : this.numbers.values()){
            if(value){
                trueCounter++;
            }
        }
        return trueCounter == this.maxNumber;
    }

    private HashMap<Integer,Boolean> generateNumbers(){
        HashMap<Integer,Boolean> output = new HashMap<>();
        for (int i = this.minNumber; i <= this.maxNumber; i++) {
            output.put(i,false);
        }
        return output;
    }

}
