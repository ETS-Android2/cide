package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 06

 Contexte --
 Realitza un programa que calcule el temps que tardarà un objecte en caure des d’una alçada h.
 Aplica la fórmula: t=2h/g essent g=9.81m/s2

 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE06 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        double g = 9.81;

        double altura;

        do {

            System.out.print("Introdueix l'altura vàlida > 0: ");

            altura = userIn.nextDouble();

        } while(altura < 0);

        System.out.println("El temps que tardarà el objecte en caure es: " + (Math.sqrt((2 * altura) / g)));
    }
}
