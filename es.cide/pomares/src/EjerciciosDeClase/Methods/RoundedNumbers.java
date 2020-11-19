package EjerciciosDeClase.Methods;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Methods    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-18

    DESCRIPTION
    (Rounding Numbers) Math.floor can be used to round values
    to the nearest integer, e.g., y = Math.floor( x + 0.5 );
    will round the number x to the nearest integer and assign
    the result to y. Write an application that reads double
    values and uses the preceding statement to round each of
    the numbers to the nearest integer. For each number processed,
    display both the original number and the rounded number.

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Carlos Pomares
 */

public class RoundedNumbers {

    private ArrayList<Double> numbers;
    private ArrayList<Double> rounded;

    public RoundedNumbers(){
        numbers = new ArrayList<>();
        rounded = new ArrayList<>();
    }

    public void addNumber(double number) {
        this.numbers.add(number);
        roundNumbers();
    }

    public void addNumber(double[] numbers){
        for (Double number : numbers){
            addNumber(number);
        }
        roundNumbers();
    }

    private void roundNumbers(){
        for(Double number : this.numbers){
            rounded.add(Math.floor(number));
        }
    }

    private LinkedHashMap<Double,Double> getNumbers(){
        LinkedHashMap<Double,Double> allNumbers = new LinkedHashMap<>();
        for (int i = 0; i < this.numbers.size(); i++) {
            allNumbers.put(this.numbers.get(i),this.rounded.get(i));
        }
        return allNumbers;
    }

    @Override
    public String toString() {
        return getNumbers().toString();
    }
}
