package EjerciciosDeClase.Bucles;

/*

 Exercicis de bucles
 Exercici 10

 Contexte --
 Fes un programa que calculi la mitjana d’un conjunt de nombres introduïts per teclat per l’usuari.
 A priori no sabem quant de nombres hi haurà, però sabem que l’usuari indicarà que no vol inserir
 més nombres quan introdueixi un nombre negatiu.


 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class SecondUnitE10 {
    public static void start(){

        // Scanner per obtenir dades de l'usuari per terminal.
        Scanner userIn = new Scanner(System.in);

        // Acumulador per sumar cada nombre amb els anteriors.
        double accumulador = 0;
        // Itermediari per poder fer un control sobre les dades
        // i clarificar quan s'introdueix una dada negativa.
        double intermediari = 0;

        // Comptador per tenir un compte de quants nombres s'han indroduit
        int comptador = 0;

        // Terminador del bucle while quan s'introdueix una dada negativa.
        boolean terminat = false;

        do {

            System.out.print("Introdueix un nombre: ");

            intermediari = userIn.nextDouble();

            // Si l'intermediari detecta un nombre negatiu termina el bucle.
            if(intermediari < 0 )
                terminat = true;

            // Acumula les noves dades amb les anteriors.
            accumulador += intermediari;

            // Suma +1 al comptador per tenir en recompte quants nombres s'han introduit.
            comptador++;

        } while (!terminat);

        // Retornam la divisió entre la suma de tots els nombres entre la quantitat de nombres introduits.
        System.out.println("La mitja dels nombres introduits es: " + (accumulador / comptador));

    }
}
