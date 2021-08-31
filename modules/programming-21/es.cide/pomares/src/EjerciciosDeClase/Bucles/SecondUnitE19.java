package EjerciciosDeClase.Bucles;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Bucles
    

    Author      Carlos Pomares
    Date        2020-11-13

    DESCRIPTION
    Fes un programa que pinti una piràmide per pantalla on l’alçada s’introduirà per teclat.
    També haurà de demanar per teclat amb quin caràcter volem pintar la piràmide.
    Si l’alçada fos 5, per exemple i el caràcter # tindríem la següent figura:
    
*/

/**
 * @author Carlos Pomares
 */

import java.util.Scanner;

public class SecondUnitE19 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        int altura;
        String caracter;

        System.out.print("Introduce el carácter: ");
        caracter = userIn.next();

        System.out.print("Introduce la altura de la pirámide: ");
        altura = userIn.nextInt();

        for (int i = 1; i <= altura ; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(caracter);
            }
            System.out.print("\n");
        }

    }
}
