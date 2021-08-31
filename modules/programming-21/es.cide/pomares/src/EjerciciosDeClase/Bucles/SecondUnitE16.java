package EjerciciosDeClase.Bucles;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Bucles
    

    Author      Carlos Pomares
    Date        2020-11-13

    DESCRIPTION
    Escriu un programa que digui si el nombre (sencer) que ens passen per
    paràmetre és un nombre primer. Un nombre primer és aquell que només és
    divisible per ell mateix i per 1.

*/

/**
 * @author Carlos Pomares
 */


public class SecondUnitE16 {
    public static void start(){

        int nombre = 12;
        int count = 0;

        for (int i = 1; i < nombre ; i++) {
            if(nombre % i == 0){
                count++;
            }
        }

        if(count > 1){
            System.out.println("No primer");
        } else {
            System.out.println("Primer");
        }

    }
}
