package EjerciciosDeClase.Bucles;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Bucles
    

    Author      Carlos Pomares
    Date        2020-11-13

    DESCRIPTION
    Fes un programa que afegeixi els 100 següents nombres a un nombre sencer
    positiu introduït per teclat. Si el nombre no és correcte, el tornarà a
    demanar fins que el nombre sigui correcte.

*/

/**
 * @author Carlos Pomares
 */

import java.util.Scanner;

public class SecondUnitE17 {
    public static void start(){
        Scanner userIn = new Scanner(System.in);

        int nombre;
        int resultado = 0;

        do {

            System.out.print("Nombre entero positivo: ");
            nombre = userIn.nextInt();

            if(nombre >= 0){
                for (int i = nombre; i <= (nombre + 100);i++) {
                    resultado += i;
                }
            }

        } while(nombre < 0);

        System.out.println("Resultado: " + resultado);

    }
}
