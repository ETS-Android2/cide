package Cinema;

/*

    Project     ED21
    Package     Cinema    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-18

    DESCRIPTION
    
*/

import Pelicula.Pelicula;
import Personas.Persona;
import Utility.ClearConsole;

/**
 * @author Carlos Pomares
 */

public class Cinema extends Thread {

    // OBJECTS
    private Persona[] asientos;
    private Pelicula film;

    // PROPERTIES
    private int type;
    private int collectedTickets;

    // STATISTICS
    public long nanotime;
    public int lastPerson = 0;

    /**
     *
     *  El constructor permite utilizar diferentes modos de aplicación,
     *  como "stats", "view" o "" que resultara en la ejecución normal.
     *
     * @param type el tipo de modo de ejecución.
     */
    public Cinema(String type){

        if(type.equals("view")){
            this.type = 1;
        } else if(type.equals("stats")){
            this.type = -1;
        } else {
            this.type = 0;
        }

        this.asientos = new Persona[72];
        this.film = new Pelicula();
        this.collectedTickets = 0;
        llenarCine();

        if(this.type == -1){
            System.out.printf("\n%-30s %-15s %-15s %-15s",
                    "FILM TITLE", "FILM TICKET", "FILM AGE","FILM DIRECTOR");
            System.out.printf("\n%-30s %-15d %-15d %-15",
                    this.film.getZoneTitle(), this.film.getZoneTicket(), this.film.getZoneAge(),this.film.getZoneDirector());

            System.out.printf("\n%-15s %-15s","TIME MS","LAST PERSON");
            System.out.printf("\n%-15f %-15d",((double)this.nanotime / 1000000),this.lastPerson);
        }

    }

    /**
     *
     * Iniciara el tiempo de estadísticas a partir de iniciar la entrada de personas en el cine
     * e inicializará el bucle que llenará el cine.
     *
     */
    private void llenarCine(){
        long startTime = System.nanoTime();
        cinemaRuntime();
        long stopTime = System.nanoTime();
        this.nanotime = (stopTime - startTime);
    }

    /**
     *
     * El bucle que llenará el cine de personas hasta que se llene.
     *
     */
    private void cinemaRuntime(){
        try {
            while(!isFilled()){
                Persona person = new Persona();
                if(checkRequirements(person)){
                    if(this.type == -1){
                        if(lastPerson() == 1) {
                            putPerson(person,true);
                        } else {
                            putPerson(person);
                        }
                    } else if(this.type == 1){
                        putPerson(person);
                        printSeats();
                        sleep(1000);
                    } else {
                        putPerson(person);
                    }
                }
            }
            this.collectedTickets = this.film.getZoneTicket() * this.asientos.length;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * Método que buscará sitio vacio, si encuentra uno lleno
     * utilizará la recursividad para volver a buscar sitio.
     *
     * @param person objeto persona.
     */
    private void putPerson(Persona person){
        int seat = (int)(Math.random() * 72);
        if(this.asientos[seat] == null){
            this.asientos[seat] = person;
        } else {
            putPerson(person);
        }
    }

    /**
     *
     * Método que buscará sitio vacio, si encuentra uno lleno
     * utilizará la recursividad para volver a buscar sitio.
     * Utilizando el marcador de ultima persona, contará el número
     * de iteraciones para encontrar sitio a la última persona.
     *
     * @param person objeto persona.
     * @param lastPerson marcador.
     */
    private void putPerson(Persona person,boolean lastPerson){
        int seat = (int)(Math.random() * 72);
        if(this.asientos[seat] == null){
            this.asientos[seat] = person;
            this.lastPerson++;
        } else {
            if(lastPerson)
                this.lastPerson++;
            putPerson(person,true);
        }
    }

    /**
     *
     * Devolvera, true si la persona pasada por parámetro comple con los requisitos de dinero y edad, sino
     * devolvera false.
     *
     * @param person el objeto persona.
     * @return si la persona cumple los requisitos.
     */
    private boolean checkRequirements(Persona person){
        return (person.getDinero() >= this.film.getZoneTicket()) && (person.getEdad() >= this.film.getZoneAge());
    }

    /**
     *
     * Contara el número de personas y devolvera si la cifra es igual a 0 o no.
     * Resultando en 0 si está lleno.
     *
     * @return si el contador es igual a 0 o no.
     */
    private boolean isFilled(){
        int falseCounter = 0;
        for(int index = 0; index < this.asientos.length; index++){
            if(asientos[index] == null){
                falseCounter++;
            }
        }
        return falseCounter == 0;
    }

    /**
     *
     * Devolvera el número de personas en el cine.
     *
     * @return el número de personas en el cine.
     */
    private int lastPerson(){
        int falseCounter = 0;
        for(int index = 0; index < this.asientos.length; index++){
            if(asientos[index] == null){
                falseCounter++;
            }
        }
        return falseCounter;
    }

    /**
     *
     * Permite imprimir por pantalla los sitios ocupados o vacios, siendo
     * estos 0 si está vacio o 1 si está ocupado.
     *
     */
    private void printSeats() {
        int counter = 1;
        System.out.print("\n\n\n");
        for(int index = 1; index <= this.asientos.length; index++){
            if(this.asientos[index - 1] == null){
                System.out.print(" 0 ");
            } else {
                System.out.print(" 1 ");
            }
            if(counter == 9){
                System.out.print("\n");
                counter = 0;
            }
            counter++;
        }
    }

}
