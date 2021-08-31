package Calculadora;

/*

    Project     ED21
    Package     Calculadora    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-12

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


import Calculadora.Operations.Operation;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora {

    private Scanner userIn;

    public static double operationResult;

    public Calculadora(){

    }

    public Calculadora(Scanner entradaDeDatos){

        // Init app configure
        userIn = entradaDeDatos;

        // Main procedure of the app
        loadMenus();
    }

    public void loadMenus(){
        mainMenu();
    }

    private void mainMenu(){

        boolean exit = false;
        int userOrder = 0;

        while(!exit){

            System.out.println("Opción 1: Sumar");
            System.out.println("Opción 2: Restar");
            System.out.println("Opción 3: Dividir");
            System.out.println("Opción 4: Multiplicar");
            System.out.println("Opción 5: Salir");

            System.out.print("\nIntroduce tu orden: ");
            try {
                userOrder = userIn.nextInt();
            } catch (Exception userError){
                System.out.println("Error.");userOrder = 0;}

            switch (userOrder){
                case 1 -> {
                    // Sumar
                    loadOperation("suma");
                    System.out.println("\nEl resultado es: " + operationResult + "\n");
                }
                case 2 -> {
                    // Restar
                    loadOperation("resta");
                    System.out.println("\nEl resultado es: " + operationResult + "\n");
                }
                case 3 -> {
                    // Dividir
                    loadOperation("division");
                    System.out.println("\nEl resultado es: " + operationResult + "\n");
                }
                case 4 -> {
                    // Multiplicar
                    loadOperation("multiplicar");
                    System.out.println("\nEl resultado es: " + operationResult + "\n");
                }
                case 5 -> // Salir
                        exit = true;
            }
        }
    }

    private void loadOperation(String operationType){
        Operation userOperation = new Operation(operationType, userIn);
    }

}
