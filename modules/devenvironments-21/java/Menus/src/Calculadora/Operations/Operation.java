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


import Calculadora.Calculadora;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Operation extends Calculadora {

    private Scanner userData;
    double x, y;

    public Operation(){}

    public Operation(String typeOfOperation, Scanner entryData){
        userData = entryData;
        loadLoop(typeOfOperation);
    }

    private void loadLoop(String operation){
        if ("suma".equals(operation)) {
            addProcedure();
        }
    }

    private void addProcedure(){
        try{
            System.out.print("Introduce el primer número: ");
            this.x = userData.nextInt();
            System.out.print("Introduce el segundo número: ");
            this.y = userData.nextInt();
            try{
                Calculadora.operationResult = new Addition().add(x,y);
            } catch (Exception error){error.printStackTrace();}
        } catch (Exception error) { error.printStackTrace(); }
    }


}
