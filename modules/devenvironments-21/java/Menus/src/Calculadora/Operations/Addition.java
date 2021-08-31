package Calculadora.Operations;

/*

    Project     ED21
    Package     Calculadora.Operations    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-12

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


public class Addition extends Operation {

    public Addition(){}

    public int add(int firstOperand, int secondOperand){
        return firstOperand + secondOperand;
    }

    public float add(float firstOperand, float secondOperand){
        return firstOperand + secondOperand;
    }

    public double add(double firstOperand, double secondOperand){
        return firstOperand + secondOperand;
    }

}
