package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 10

 Contexte --
 Escriu un programa que ens digui l’horòscop al que es pertany a partir del dia i mes de naixement..

 @author     Carlos Pomares
 Date        2020-11-9

 */

import java.util.Scanner;

public class ConditionalsE10 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        String[] fechaDeNacimiento;

        System.out.print("Introduce tu la fecha de tu nacimiento (dd-MM): ");
        fechaDeNacimiento = userIn.nextLine().split("-");

        /*

            Horoscopo:

            Enero       1   31
            Febrero     2   28-29
            Marzo       3   31
            Abril       4   30
            Mayo        5   31
            Junio       6   30
            Julio       7   31
            Agosto      8   31
            Septiembre  9   30
            Octubre     10  31
            Noviembre   11  30
            Diciembre   12  31

            Aries (21 de Marzo - 20 de Abril)
            Tauro (21 de Abril - 20 de Mayo)
            Géminis (21 de Mayo - 20 de Junio)
            Cáncer (21 de Junio - 20 de Julio)
            Leo (21 de Julio - 21 de Agosto)
            Virgo (22 de Agosto - 22 de Septiembre)
            Libra (23 de Septiembre - 22 de Octubre)
            Escorpio (23 de Octubre - 22 de Noviembre)
            Sagitario (23 de Noviembre - 20 de Diciembre)
            Capricornio (21 de Diciembre - 19 de Enero)
            Acuario (20 de Enero - 18 de Febrero)
            Piscis (19 de Febrero - 20 de Marzo)

         */

        // La comprobación de la fecha es individual y por ello cada una de las siguientes declaraciones
        // Son repetitivas y por ello si se quiere analizar debe coger únicamente una.

        /*

            Para saber los días en un comentario anterior se especifican los valores intermedios de cada uno de los signos

            if((fechaDeNacimiento[1].contains("3")  En esta parte el número 3, simboliza el mes.
                &&
                    (Integer.parseInt(fechaDeNacimiento[0]) >= 21
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31)) // La siguiente sentencia declara los días desde donde parte en ese mes
                || El operador OR dictamina que mes a mes va.
                (fechaDeNacimiento[1].contains("4")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                            && Integer.parseInt(fechaDeNacimiento[0]) <= 20)))
        {
            System.out.println("Tu signo del zodiaco es: Aries");
        }

        */

        if((fechaDeNacimiento[1].contains("3")
                &&
                    (Integer.parseInt(fechaDeNacimiento[0]) >= 21
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31))
                ||
                (fechaDeNacimiento[1].contains("4")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                            && Integer.parseInt(fechaDeNacimiento[0]) <= 20))) // Aries
        {
            System.out.println("Tu signo del zodiaco es: Aries");
        } else if((fechaDeNacimiento[1].contains("4")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 21
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 30))
                ||
                (fechaDeNacimiento[1].contains("5")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 20))) // Tauro
        {
            System.out.println("Tu signo del zodiaco es: Tauro");
        } else if((fechaDeNacimiento[1].contains("5")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 21
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31))
                ||
                (fechaDeNacimiento[1].contains("6")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 20))) // Géminis
        {
            System.out.println("Tu signo del zodiaco es: Géminis");
        } else if((fechaDeNacimiento[1].contains("6")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 21
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 30))
                ||
                (fechaDeNacimiento[1].contains("7")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 20))) // Cáncer
        {
            System.out.println("Tu signo del zodiaco es: Cáncer");
        } else if((fechaDeNacimiento[1].contains("7")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 21
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31))
                ||
                (fechaDeNacimiento[1].contains("8")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 21))) // Leo
        {
            System.out.println("Tu signo del zodiaco es: Leo");
        } else if((fechaDeNacimiento[1].contains("8")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 22
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31))
                ||
                (fechaDeNacimiento[1].contains("9")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 22))) // Virgo
        {
            System.out.println("Tu signo del zodiaco es: Virgo");
        } else if((fechaDeNacimiento[1].contains("9")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 23
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 30))
                ||
                (fechaDeNacimiento[1].contains("10")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 22))) // Libra
        {
            System.out.println("Tu signo del zodiaco es: Libra");
        } else if((fechaDeNacimiento[1].contains("10")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 23
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31))
                ||
                (fechaDeNacimiento[1].contains("11")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 22))) // Escorpio
        {
            System.out.println("Tu signo del zodiaco es: Escorpio");
        } else if((fechaDeNacimiento[1].contains("11")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 23
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31))
                ||
                (fechaDeNacimiento[1].contains("12")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 20))) // Sagitario
        {
            System.out.println("Tu signo del zodiaco es: Sagitario");
        } else if((fechaDeNacimiento[1].contains("12")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 21
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 30))
                ||
                (fechaDeNacimiento[1].contains("1")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 19))) // Capricornio
        {
            System.out.println("Tu signo del zodiaco es: Capricornio");
        } else if((fechaDeNacimiento[1].contains("1")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 20
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 31))
                ||
                (fechaDeNacimiento[1].contains("2")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 18))) // Acuario
        {
            System.out.println("Tu signo del zodiaco es: Acuario");
        } else if((fechaDeNacimiento[1].contains("2")
                &&
                (Integer.parseInt(fechaDeNacimiento[0]) >= 19
                        && Integer.parseInt(fechaDeNacimiento[0]) <= 29))
                ||
                (fechaDeNacimiento[1].contains("3")
                        &&
                        (Integer.parseInt(fechaDeNacimiento[0]) >= 1
                                && Integer.parseInt(fechaDeNacimiento[0]) <= 20))) // Piscis
        {
            System.out.println("Tu signo del zodiaco es: Piscis");
        } else {
            System.out.println("Fecha no válida. Reinicie el programa...");
        }
    }
}
