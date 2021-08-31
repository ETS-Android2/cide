package EjerciciosDeClase.Methods;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Methods    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-24

    DESCRIPTION
    (Exponentiation) Write a method integerPower(base, exponent)
    that returns the value of base exponent. For example,
    integerPower(3, 4) calculates (3 * 3 * 3 * 3). Assume that
    exponent is a positive, nonzero integer and that base is an integer.
    Use a for or while statement to control the calculation. Do not use
    any Math class methods. Incorporate this method into an application
    that reads integer values for base and exponent and performs the
    calculation with the integerPower method.
    
*/

/**
 * @author Carlos Pomares
 */


public class Exponentiation {
    public int integerPower(int base, int exponent){
        int output = 1;
        int i = 0;
        while (i < exponent) {
            output *= base;
            i++;
        }
        return output;
    }
}
