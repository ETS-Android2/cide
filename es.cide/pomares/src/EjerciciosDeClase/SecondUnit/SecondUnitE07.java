package EjerciciosDeClase.SecondUnit;

/*

 Unitat 2 -- Exercicis de bucles
 Exercici 07

 Contexte --
 Realitza el control d’accés a una caixa forta. La combinació ha de ser de 4 xifres.
 El programa ens demanarà la combinació. Si no encertem, ens mostrarà el missatge “Ho sentim,
 combinació incorrecta” i tornarà a demanar la combinació.
 Si encertem ens mostrarà “Combinació correcta” i acabarà. Tindrem 4 oportunitats per encertar la combinació.


 author     Carlos Pomares
 Date        2020-11-6

 */

import java.util.Scanner;

public class SecondUnitE07 {
    public static void start(){

        // Objecte scanner per obtenir dades de l'usuari.
        Scanner userIn = new Scanner(System.in);

        /*
            Differents variables per tenir en compte el nombre d'intents fets,
            la combinació y si l'usuari ha encertat.
        * */
        int intents = 4;
        int combinacio = 1234;
        boolean encertat = false;

        // El bucle demanara sempre que intents sigui major a 0 i encertat sigui fals
        do {
            // S'excuta una vegada i pregunta la combinació
            System.out.print("Introdueix la combinació: ");


            if(userIn.nextInt() == combinacio){
                // Si la combinació es correcte tornara aquest missatge i terminarà el programa.
                System.out.println("Combinació correcta!");
                encertat = true;
            } else {
                // Si es incorrecte o tornarà a demanar fins 4 vegades si no encerta, el programa terminarà.
                System.out.println("Ho sentim, combinació incorrecta.");
                intents--;
            }
        } while(intents > 0 && !encertat);


    }
}
