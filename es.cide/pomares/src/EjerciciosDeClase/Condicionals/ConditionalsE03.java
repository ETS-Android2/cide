package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 03

 Contexte --
 Escriu un programa que donat un nombre del 1 al 7 escrigui el corresponent nom del dia de la setmana.

 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE03 {
    public static void start(){

        // Matriu de dies
        String[] dies = new String[]{
                "Lunes",
                "Martes",
                "Miercoles",
                "Jueves",
                "Viernes",
                "Sabado",
                "Domingo"
        };

        // Obtenim dades de l'usuari.
        Scanner userIn = new Scanner(System.in);

        System.out.print("Introdueix un nombre del 1 al 7: ");

        // Segons quin nombre introdueix correspon a un dia de la setmana.
        System.out.println("El dia de la setmana correspon: " + (dies[userIn.nextInt() - 1]));

    }
}
