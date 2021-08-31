package Personas;

/*

    Project     ED21
    Package     Personas    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-18

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class Persona {

    private int edad;
    private int dinero;

    /**
     *
     * Constructor que genera la edad y dinero de la persona.
     *
     */
    public Persona(){
        this.edad = generarEdad();
        this.dinero = generarDinero();
    }

    public int getEdad() {
        return edad;
    }

    public int getDinero() {
        return dinero;
    }

    /**
     *
     * Generará la edad entre 0 y 100;
     *
     * @return la edad de la persona.
     */
    private int generarEdad(){
        return (int)(Math.random() * 100 + 1);
    }

    /**
     *
     * Generará el dinero entre 0 y 50;
     *
     * @return el dinero de la persona.
     */
    private int generarDinero(){
        return (int)(Math.random() * 50 + 1);
    }

}
