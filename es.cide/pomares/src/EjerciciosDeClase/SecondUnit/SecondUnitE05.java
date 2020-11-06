package EjerciciosDeClase.SecondUnit;

/**

 Unitat 2 -- Exercicis de bucles
 Exercici 05

 Contexte --
 Mostra els números de 320 a 160, comptant de 20 en 20 cap endarrera utilitzant un bucle while.

 @author     Carlos Pomares
 Date        2020-11-6

 */

public class SecondUnitE05 {
    public static void start(){

        // Identificador d'exercici
        System.out.println("Exercici 05");

        /*
         *  Amb aquest programa, si el comptador es divisible entre 20 i el seu reste dona 0,
         *  donara una sortida per pantalla.
         * */
        int comptador = 320;
        while(comptador >= 160){
            if(comptador % 20 == 0)
                System.out.println(comptador);
            comptador--;
        }

    }
}
