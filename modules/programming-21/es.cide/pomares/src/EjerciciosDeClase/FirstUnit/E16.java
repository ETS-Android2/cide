package EjerciciosDeClase.FirstUnit;
/*

	Exercici 16

	Contexte:
	Realitza en Java un conversor de Mb a Kb.

	Alumne: Carlos Pomares Parpal
	Data: 22-10-2020

*/

import java.util.Scanner;

public class E16 {

    public static void main(String[] args){

        // 1 Mb son 1000Kb

        // Instanciam l'objecte Scanner per obtenir dades de l'usuari.
        Scanner userIn = new Scanner(System.in);

        // Variable per poder processar les dades de l'usuari.
        String userOrder;

        // Variable per poder processar la conversió.
        long toConvert;

        System.out.println("Conversor de Mb->Kb || Kb->Mb");

        // Obtenim l'ordre
        System.out.print("Mb -- Kb: ");
        userOrder = userIn.nextLine().toLowerCase();

        switch(userOrder){
            // Si es mb, farem la conversió de Mb a Kb
            case "mb":
                System.out.print("Introduce el número de Megabits: ");
                toConvert = userIn.nextLong();
                // Per aquesta conversió de Mb a Kb es multiplica el nombre de Mb per 1024.
                System.out.println("Resultado: " + toConvert + "Mb son " + (toConvert * 1024) + "Kb");
                break;

            // Si es mb, farem la conversió de Kb a Mb
            case "kb":
                System.out.print("Introduce el número de Kilobits: ");
                toConvert = userIn.nextLong();
                // Per aquesta conversió de Kb a Mb es divideix el nombre de Kb per 1024.
                System.out.println("Resultado: " + toConvert + "Kb son " + (toConvert / 1024) + "Mb");
                break;
        }
    }
}