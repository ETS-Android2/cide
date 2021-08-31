package EjerciciosDeClase.Bucles;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Bucles

    Author      Carlos Pomares
    Date        2020-11-13

    DESCRIPTION
    Escriu un programa que demani dos nombres (sencers positius) que seran la base i l’exponent per a
    calcular la seva potència. Si detecta que qualsevol entrada no és un nombre correcte, donarà un missatge i s’aturarà.
    
*/

/**
 * @author Carlos Pomares
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class SecondUnitE14 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);
        int[] nombres = new int[2];
        int resultat = 1;

        try {

            System.out.print("Introduce la base: ");
            nombres[0] = userIn.nextInt();
            
            System.out.print("Introduce el exponente: ");
            nombres[1] = userIn.nextInt();

            if(nombres[0] >= 0 && nombres[1] >= 0){
                for (int i = 0; i < nombres[1] ; i++) {
                    resultat *= nombres[0];
                }
            }
        } catch (InputMismatchException e){e.printStackTrace();}

        System.out.println("El resultado es: " + resultat);

    }
}
