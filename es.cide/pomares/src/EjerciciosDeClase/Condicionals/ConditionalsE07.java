package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 07

 Contexte --
 Fes un programa que calculi la mitjana aritmètica de tres notes.

 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE07 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        float[] notas = new float[3];

        boolean terminat = false;

        int comptador = 0;

        do {

            System.out.print("Introduce la nota número " + (comptador + 1) + " : ");

            notas[comptador] = userIn.nextFloat();

            comptador++;

            if(comptador == 3)
                terminat = true;

        } while(!terminat);

        System.out.println("La media aritmética de " + notas[0] + ", "
                + notas[1] + ", " + notas[2] + " es: "
                + ((notas[0] + notas[1] + notas[2]) / 3));

    }
}
