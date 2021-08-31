package EjerciciosDeClase.Condicionals;

/*

 Exercicis de condicionals
 Exercici 08

 Contexte --
 Sobre l’exercici 7, fes que a més a més ens digui la nota en
 caràcters (insuficient, suficient, bé, notable o excel·lent)

 @author     Carlos Pomares
 Date        2020-11-7

 */

import java.util.Scanner;

public class ConditionalsE08 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        float[] notas = new float[3];
        String[] stat = new String[3];

        boolean terminat = false;

        int comptador = 0;

        do {

            System.out.print("Introduce la nota número " + (comptador + 1) + " : ");

            notas[comptador] = userIn.nextFloat();

            comptador++;

            if(comptador == 3)
                terminat = true;

        } while(!terminat);

        for(comptador = 0; comptador < notas.length; comptador++){
            if (notas[comptador] >= 0 && notas[comptador] < 5) {
                stat[comptador] = "insuficient";
            } else if (notas[comptador] >= 5 && notas[comptador] < 6){
                stat[comptador] = "suficient";
            } else if (notas[comptador] >= 6 && notas[comptador] < 9){
                stat[comptador] = "notable";
            } else if (notas[comptador] >= 9 && notas[comptador] < 10){
                stat[comptador] = "excel·lent";
            }
        }

        System.out.println("La media aritmética de " + notas[0] + "("+stat[0]+"), "
                + notas[1] + "("+stat[1]+"), " + notas[2] +"("+stat[2]+") es: "
                + ((notas[0] + notas[1] + notas[2]) / 3));

    }
}
