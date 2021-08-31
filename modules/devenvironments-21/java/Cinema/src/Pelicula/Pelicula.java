package Pelicula;

/*

    Project     ED21
    Package     Pelicula    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-22

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class Pelicula {

    private String zoneTitle;
    private String zoneDirector;
    private int zoneAge;
    private int zoneTicket;

    /**
     *
     * Instanciará una pelicula generando su título, edad de visionado y dinero necesitado para entrar.
     *
     */
    public Pelicula(){
        this.zoneTitle = generateTitle();
        this.zoneDirector = generateDirector();
        this.zoneAge = generateAge();
        this.zoneTicket = generateTicket();
    }

    public String getZoneTitle() {
        return zoneTitle;
    }

    public String getZoneDirector() {
        return zoneDirector;
    }

    public int getZoneAge() {
        return zoneAge;
    }

    public int getZoneTicket() {
        return zoneTicket;
    }

    /**
     *
     * Generará un título a partir de las lista de títulos incluidos.
     *
     * @return el nombre de la película.
     */
    private String generateTitle(){
        String[] titles = new String[]{
                "Black Hawk Derribado",
                "Avengers: End Game",
                "Avatar",
                "SpiderMan 3",
                "Man in Black"
        };
        return titles[(int)(Math.random() * 5)];
    }

    /**
     *
     * Generará un director a partir de las lista de directores incluidos.
     *
     * @return el nombre de la película.
     */
    private String generateDirector(){
        String[] directors = new String[]{
                "Bill Spellman",
                "Christofer Robinson",
                "James Camarón",
                "Paqui Rin",
                "Elsa Pataki"
        };
        return directors[(int)(Math.random() * 5)];
    }

    /**
     *
     * Generará un precio aleatorio entre 0 y 50€.
     *
     * @return el precio de la entrada.
     */
    private int generateTicket(){
        return (int)(Math.random() * 50 + 1);
    }

    /**
     *
     * Generará una edad aleatoria entre 0 y 100.
     *
     * @return la edad permitida.
     */
    private int generateAge(){
        return (int)(Math.random() * 100 + 1);
    }

}