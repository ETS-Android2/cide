package EjerciciosDeClase.Bucles;

/*

 Unitat 2 -- Exercicis de bucles
 Exercici 09

 Contexte --
 Fes un programa que ens digui quants de dígits té un nombre introduït per teclat.

 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class SecondUnitE09 {
    public static void start(){

        // Objecte scanner per obtenir dades de l'usuari.
        Scanner userIn = new Scanner(System.in);

        // Variable nombre per enmagatzemar el nombre introduit per l'usuari.
        double nombre = 0;

        System.out.print("Introdueix el nombre: ");

        nombre = userIn.nextDouble();

        // Convertim a string el nombre, per després fer una normalització, suprimint el . , i finalment obtenim la mida.
        System.out.println("El nombre " + nombre + " te " +
                (Double.toString(nombre).replace(".","").length()) + " digits.");

    }
}
