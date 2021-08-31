/*

    Project     ED21
        
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-04

    DESCRIPTION
    
*/

import Estanteria.Estanteria;
import Estanteria.Libro;

/**
 * @author Carlos Pomares
 */

public class EstanteriaTest {

    public static void main(String[] args) {

        Estanteria libreria = new Estanteria();
        Libro l1 = new Libro("Los Mil Viajes", "Antonio Machete",6.5f);
        Libro l2 = new Libro("El cementerio de Benji", "Pepe Bichuela",2.5f);
        Libro l3 = new Libro("Interstellar", "Serpi Serpe",8.2f);
        Libro l4 = new Libro("Madriguera Húmeda", "John A.K.A Dos",7.7f);
        Libro l5 = new Libro("Viaje al centro de la tierra", "Kolakoe",3.8f);

        try {
            libreria.afegirLlibre(l1);
            libreria.afegirLlibre(l2);
            libreria.afegirLlibre(l3);
            libreria.afegirLlibre(l4);
            libreria.afegirLlibre(l5);
            libreria.veureTop();
            libreria.eliminarLlibre("Interstellar","titulo");
            libreria.veureEstanteria();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
