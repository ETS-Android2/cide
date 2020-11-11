package EjerciciosDeClase.Bucles;

/*

    Programming21 --- EjerciciosDeClase.Bucles

    Bucles
    Exercise 12
    
    Description:
    Escriu un programa que mostri els n primers termes de la successió (série)
    de Fibonacci i el nombre n ha de ser introduït per teclat
    
    version     1.0
    author      Carlos Pomares
    created     2020-11-10

*/

import java.util.Scanner;

public class SecondUnitE12 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        int fibo,anterior = 0;

        int actual = 1,temporal;

        System.out.print("Introduce un número para calcular la série de fibonacci: ");
        fibo = userIn.nextInt();

        for (int i = 1; i <= fibo ; i++) {

            temporal = anterior;
            anterior += actual;
            actual = temporal;

            System.out.println(actual);

        }
    }
}
