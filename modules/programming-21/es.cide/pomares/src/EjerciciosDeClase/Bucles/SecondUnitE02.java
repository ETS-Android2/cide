package EjerciciosDeClase.Bucles;

/**

    Unitat 2 -- Exercicis de bucles
    Exercici 02

    Contexte --
    Escriviu un programa que mostri els múltiples de 5 de 0 a 100 utilitzant un bucle while.

    @author     Carlos Pomares
    Date        2020-11-6

 */

public class SecondUnitE02 {
    public static void start(){

        // Identificador d'exercici
        System.out.println("Exercici 02");

        /* Feim un bucle, que quant el reste de la divisiò del comptador entre 5 doni 0,
         * significarà que es un múltiple de 5 per aixo quan pasí, el passam per pantalla. */

        // Amb el bucle while, no te un comptador built-in, de manera que hem de fer un.
        int comptador = 0;
        while(comptador <= 100){
            if((comptador % 5) == 0)
                System.out.println(comptador);
            comptador++;
        }
    }
}
