package EjerciciosDeClase.Condicionals;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Condicionals    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-16

    DESCRIPTION
    Escriu un programa que donada una hora concreta (hores i minuts)
    calculi els segons que falten per arribar a mitjanit.

*/

import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class ConditionalsE11 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        String[] entrada;

        final int HORA = 86400;
        int hora, minuto;

        System.out.print("Introduce la hora (hh:mm): ");

        try {
            entrada = userIn.nextLine().split(":");

            hora = Integer.parseInt(entrada[0]);
            minuto = Integer.parseInt(entrada[1]);

            if((hora >= 0 && hora <= 24) && (minuto >= 0 && minuto <= 59)){
                hora = hora * 60 * 60;
                minuto = minuto * 60;
                hora += minuto;
                System.out.println("Quedan " + (HORA - hora) + " segundos para medianoche.");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e){ e.printStackTrace(); }
    }
}
