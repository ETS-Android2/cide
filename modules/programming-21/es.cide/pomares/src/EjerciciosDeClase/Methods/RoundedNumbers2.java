package EjerciciosDeClase.Methods;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Methods    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-24

    DESCRIPTION
    (Rounding Numbers) To round numbers to specific
    decimal places, use a statement like y =
    Math.floor( x * 10 + 0.5 ) / 10; which rounds x
    to the tenths position (i.e., the first position
     to the right of the decimal point), or y =
     Math.floor( x * 100 + 0.5 ) / 100; which rounds x
     to the hundredths position (i.e., the second
     position to the right of the decimal point).
     Write an application that defines four methods
     for rounding a number x in various ways:

     For each value read, your program should display
     the original value, the number rounded to the
      nearest integer, the number rounded to the nearest
       tenth, the number rounded to the nearest hundredth
       and the number rounded to the nearest thousandth.
    
*/

/**
 * @author Carlos Pomares
 */


public class RoundedNumbers2 {

    public void roundToAll(double number){
        System.out.printf("\n%-15s %-6s\n%-15s %-6f\n%-15s %-6d\n%-15s %-6d\n%-15s %-6d\n%-15s %-6d",
                "Type","Value",
                "Original",number,
                "Integer",roundToInteger(number),
                "Tenths",roundToTenths(number),
                "Hundredths",roundToHundredths(number),
                "Thousandths",roundToThousandths(number)
        );
    }

    private int roundToInteger(double number){
        double roundedNumber = Math.floor(number + 0.5);
        return (int)roundedNumber;
    }

    private int roundToTenths(double number){
        double roundedNumber = Math.floor(number * 10 + 0.5);
        return (int)roundedNumber;
    }

    private int roundToHundredths(double number){
        double roundedNumber = Math.floor((number * 100) + (0.5 / 100));
        return (int)roundedNumber;
    }

    private int roundToThousandths(double number){
        double roundedNumber = Math.floor((number * 1000) + (0.5 / 1000));
        return (int)roundedNumber;
    }

}
