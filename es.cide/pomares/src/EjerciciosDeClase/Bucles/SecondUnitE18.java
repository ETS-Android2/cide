package EjerciciosDeClase.Bucles;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Bucles
    

    Author      Carlos Pomares
    Date        2020-11-13

    DESCRIPTION
    Fes un programa que obtingui i mostri els nombres sencers
    entre dos nombres introduïts per teclat que el programa validi
    que siguin diferents, de manera que hauria de començar des del
    més petit amb increments de 7 en 7 fins arribar al més gran.

*/

import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class SecondUnitE18 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        int a,b;

        System.out.print("Primer número: ");
        a = userIn.nextInt();

        System.out.print("Segundo número: ");
        b = userIn.nextInt();

        if(a < b) { for (int i = a; i <= b ; i++) { System.out.println(i); }}
        if(a > b) { for (int i = b; i <= a ; i++) { System.out.println(i); }}
        if(a == b) {
            System.out.println("Son iguals");
        }

    }
}
