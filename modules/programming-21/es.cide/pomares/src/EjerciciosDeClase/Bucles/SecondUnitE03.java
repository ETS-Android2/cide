package EjerciciosDeClase.Bucles;

/**

 Unitat 2 -- Exercicis de bucles
 Exercici 03

 Contexte --
 Escriviu un programa que mostre els múltiples de 5 de 0 a 100 utilitzant un bucle do / while.

 @author     Carlos Pomares
 Date        2020-11-6

 */

public class SecondUnitE03 {
    public static void start() {

        // Identificador d'exercici
        System.out.println("Exercici 03");

        /* Feim un bucle, que quant el reste de la divisiò del comptador entre 5 doni 0,
         * significarà que es un múltiple de 5 per aixo quan pasí, el passam per pantalla. */

        // En aquest cas com a mínim executam 1 vegada.
        int comptador = 0;
        do {
            if((comptador % 5) == 0)
                System.out.println(comptador);
            comptador++;
        } while (comptador <= 100);
    }
}
