package EjerciciosDeClase.Arrays;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Arrays    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-12

    DESCRIPTION
    
*/

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class ArraysE02 {
    public static void start(){

        ArrayList<Integer> nombres = new ArrayList<Integer>();

        int suma = 0;
        int media;
        int max = 0;
        int min = 0;
        boolean firstIteration = true;

        for(int i = 0; i <= 20; i++){
            nombres.add((int)((Math.random() * ((i+1)*10))));
        }

        // SUMA
        for(Integer nombre : nombres){
            if(firstIteration){
                min = nombre;
                firstIteration = false;
            }
            suma += nombre;
            if(nombre > max) max=nombre;
            if(nombre < min) min=nombre;
        }
        // MEDIA
        media = suma / nombres.size();

        System.out.println(nombres + "\n\n");
        System.out.printf("SUMA: %d -- MEDIA: %d -- MAX: %d -- MIN: %d",suma,media,max,min);

    }
}
