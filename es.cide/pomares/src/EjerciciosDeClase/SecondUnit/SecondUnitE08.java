package EjerciciosDeClase.SecondUnit;

/*

 Unitat 2 -- Exercicis de bucles
 Exercici 08

 Contexte --
 Fes un programa que demana un nombre de 0 a 9 i que a continuació mostri la taula de multiplicació
 corresponent al nombre inserit. Si l’usuari introdueix un nombre diferent a 0-9,
 tornarà a demanar el nombre fins que s’introdueixi un nombre correcte.

 author     Carlos Pomares
 Date        2020-11-6

 */

import java.util.Scanner;

public class SecondUnitE08 {
    public static void start(){

        // Objete per obtenir dades de l'usuari.
        Scanner userIn = new Scanner(System.in);

        // Variables per enmagatzemar el nombre introduit i una booleana per terminar el bucle.
        int nombre = 0;
        boolean pass = false;

        do {

            // L'usuari introdueix la dada almenys una vegada.
            System.out.print("Introdueix un nombre del 0 al 9: ");
            nombre = userIn.nextInt();

            // Comprobacio si la dada està entre 0 i 9
            if(nombre >= 0 && nombre <= 9){
                // Un bucle for per fer la taula de multiplicació.
                for(int comptador = 0; comptador <= 10; comptador++){
                    System.out.println(nombre + " * " + comptador + " = " + (nombre*comptador));
                }
                // Finalitzam el bucle.
                pass = true;
            } else {
                // Donam per sortida que es invalid perque l'usuari introduesqui altra vegada el nombre.
                System.out.println("Nombre invalid, introdueix un nombre del 0 al 9.");
            }

        } while (!pass);

    }
}
