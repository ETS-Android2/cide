package EjerciciosDeClase.Bucles;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Bucles
    

    Author      Carlos Pomares
    Date        2020-11-13

    DESCRIPTION
    Escriu un programa que donats dos nombres, un nombre real (base) anomenat base
    i un sencer positiu (exponent) anomenat exponent, mostri totes potències de base
    des de 0 fins a exponent. Per exemples si base=2 i exponent=5, aleshores mostrarà
    1, 2, 4, 8, 16, 32. No podeu utilitzar funcions que implementin l’exponenciació.
    
*/

/**
 * @author Carlos Pomares
 */


public class SecondUnitE15 {
    public static void start(){

        int base = 2, exponent = 5;
        int resultat = 1;

        for (int i = 0; i < exponent ; i++) {
            System.out.println(resultat);
            resultat *= base;
        }

    }
}
