package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 01

 Contexte --
 Escriu un programa que demani per teclat un dia de la setmana (caràcters)
 i digui quina assignatura toca a primera hora d’aquell dia.

 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE01 {
    public static void start(){

        // Matriu d'assignatures.
        String[] assignatures = new String[]{
                "Sistemes Operatius",
                "Formació i Orientació Laboral",
                "Programació"
        };

        // Objecte scanner per obtenir dades de l'usuari mitjançant la consola.
        Scanner userIn = new Scanner(System.in);

        // Objecte string per enmagatzemar la dada introduida per l'usuari.
        String assignaturaDia;
        // Boolean per terminar el bucle una vegada aconseguit l'objectiu.
        boolean terminat = false;

        do {

            // Demanam el día.
            System.out.print("Introdueix un día: ");
            assignaturaDia = userIn.nextLine();

            // Sequencia de if / if else per cada un dels dies.
            if (assignaturaDia.toLowerCase().contains("dilluns")) {
                System.out.println("Dilluns -- Primera assignatura: " + assignatures[1]);
                terminat = true;
            } else if(assignaturaDia.toLowerCase().contains("dimarts")){
                System.out.println("Dimarts -- Primera assignatura: " + assignatures[1]);
                terminat = true;
            } else if(assignaturaDia.toLowerCase().contains("dimecres")){
                System.out.println("Dimecres -- Primera assignatura: " + assignatures[2]);
                terminat = true;
            } else if(assignaturaDia.toLowerCase().contains("dijous")){
                System.out.println("Dijous -- Primera assignatura: " + assignatures[1]);
                terminat = true;
            } else if(assignaturaDia.toLowerCase().contains("divendres")){
                System.out.println("Divendres -- Primera assignatura: " + assignatures[0]);
                terminat = true;
            }

        } while(!terminat);
    }
}
