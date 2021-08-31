package EjerciciosDeClase.Bucles;

/*

 Exercicis de bucles
 Exercici 11

 Contexte --
 Escriu un programa que mostri en tres columnes diferents,
 el quadrat i el cub dels 5 primers nombres sencers que l’usuari hagi introduït per teclat.


 @author     Carlos Pomares
 Date        2020-11-7

 */

// Custom class for console colors.
import Utilitats.ConsoleColors;

import java.util.Scanner;

public class SecondUnitE11 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        int[] nombres = new int[5];
        int comptador = 0;

        System.out.println(ConsoleColors.stringColor(ConsoleColors.BLUE,"A continuació introduesqui 5 nombres sensers."));

        do {

            System.out.print(ConsoleColors.stringColor(ConsoleColors.RED,"Introdueix un nombre: "));

            nombres[comptador] = userIn.nextInt();

            comptador++;

        } while(comptador <= 4);

        System.out.println("N -- 2 -- 3");

        for(comptador = 0;comptador < nombres.length; comptador++){
            System.out.println(ConsoleColors.stringColor(ConsoleColors.BLUE,nombres[comptador] + "" ) +
                    " -- " + (nombres[comptador] * nombres[comptador]) + " -- "
                    + (nombres[comptador] * nombres[comptador] * nombres[comptador]));
        }
    }
}
