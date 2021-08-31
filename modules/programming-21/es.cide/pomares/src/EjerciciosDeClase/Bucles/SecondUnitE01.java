package EjerciciosDeClase.Bucles;

/**

    Unitat 2 -- Exercicis de bucles
    Exercici 01

    Contexte --
    Escriviu un programa que mostri els múltiples de 5 de 0 a 100 utilitzant un bucle for.

    @author     Carlos Pomares
    Date        2020-11-6

 */

public class SecondUnitE01 {
    public static void start(){

        // Identificador d'exercici
        System.out.println("Exercici 01");

        /* Feim un bucle, que quant el reste de la divisiò del comptador entre 5 doni 0,
        * significarà que es un múltiple de 5 per aixo quan pasí, el passam per pantalla. */
        for(int comptador = 0; comptador <= 100; comptador++){
            if((comptador % 5 ) == 0)
                System.out.println(comptador);
        }
    }
}
