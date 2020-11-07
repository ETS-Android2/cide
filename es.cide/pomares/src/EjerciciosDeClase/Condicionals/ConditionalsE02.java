package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 02

 Contexte --
 Escriviu un programa que mostri “Bon dia”, “Bona tarda”, “Bon vespre” o “Bona nit”
 segons la hora que s’introdueixi per teclat.
 De 6 a 12, bon dia; de 13 a 19, bona tarda; de 20 a 22, bon vespre
 i de 23 a 5, bona nit. Basta s’introdueixin les hores. No es necessari considerar els minuts.

 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE02 {
    public static void start(){

        final int HORAS = 24;

        Scanner userIn = new Scanner(System.in);
        int horaIntroducida = 0;

        do {

            System.out.print("Introduce una hora: ");
            horaIntroducida = userIn.nextInt();

            if (horaIntroducida >= 6 && horaIntroducida <= 12 ){
                System.out.println("Bon día");
            } else if(horaIntroducida >= 13 && horaIntroducida <= 19){
                System.out.println("Bona tarda");
            } else if(horaIntroducida >= 20 && horaIntroducida <= 22){
                System.out.println("Bon vespre");
            } else if(horaIntroducida == 23 || (horaIntroducida >= 0 && horaIntroducida <= 5)){
                System.out.println("Bona nit");
            }

        } while(horaIntroducida < 0 || horaIntroducida > 24);

    }
}
