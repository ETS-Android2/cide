package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 05

 Contexte --
 Escriu un programa que resolgui una eqüació de primer grau (tipus ax+b=0).


 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE05 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        double a, b, x[] = new double[3], y;
        String equacio;

        System.out.print("Introdueix l'equació de primer grau (ax+b=0): ");

        equacio = userIn.nextLine();

        a = Double.parseDouble(equacio.substring(0,2).replace("x",""));
        b = Double.parseDouble(equacio.substring(2,8).replace("+",""));
        y = Double.parseDouble(equacio.substring(9).replace("=",""));

        x[0] = (y - (b));
        x[1] = a;
        x[2] = x[0] / x[1];

        System.out.println("Per l'equació dada " + equacio + " el resultat es: x = " + x[0] +
                " / " + x[1] + " , que dona un resultat de " + x[2]);

    }
}
