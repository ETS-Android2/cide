package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 04

 Contexte --
 Escriu un programa que calculi el sou setmanal d’un treballador tenint
 en compte que les hores ordinàries (40 primeres hores de treball)
 es paguen a 12€/hora. A partir de l’hora 41, es paguen a 16€/hora.


 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE04 {
    public static void start(){

        final float ORDINARIES = 12;
        final float EXTRAORDINARIES = 16;

        Scanner userIn = new Scanner(System.in);

        float horesTreballades = 0;

        System.out.print("Introdueix les hores treballades: ");
        horesTreballades = userIn.nextFloat();

        if(horesTreballades <= 40){
            System.out.println("El sou setmanal del traballador es: " + (horesTreballades * ORDINARIES));
        } else {
            System.out.println("El sou setmanal del traballador es: " +
                    (((horesTreballades - (horesTreballades - 40)) * ORDINARIES) +
                            ((horesTreballades - 40) * EXTRAORDINARIES)));
        }

    }
}
