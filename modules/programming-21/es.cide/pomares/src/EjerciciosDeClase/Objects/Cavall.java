package EjerciciosDeClase.Objects;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Objects    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-17

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class Cavall {

    /*
        Atributos
     */

    private String nombre;
    private String color;
    private int edad;
    private String owner;
    private String etnia;

    private boolean limpio = false;
    private boolean haComido = false;
    private boolean saltando = false;
    private boolean galopando = false;

    /*
        Métodos
     */

    // Constructor
    public Cavall(){}

    // Constructor
    public Cavall(String nombre, int edad, String color, String owner, String etnia){
        this.nombre = nombre;
        try {
            setEdad(edad);
        } catch (Exception e) { e.getMessage(); }
        this.color = color;
        this.owner = owner;
        this.etnia = etnia;
    }

    // Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setEdad(int edad) throws Exception{
        if(edad < 0)
            throw new Exception("Value under 0.");
        this.edad = edad;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }
    public void setLimpio(boolean limpio) {
        this.limpio = limpio;
    }
    public void setHaComido(boolean haComido) {
        this.haComido = haComido;
    }
    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }
    public void setGalopando(boolean galopando) {
        this.galopando = galopando;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }
    public String getColor() {
        return color;
    }
    public int getEdad() {
        return edad;
    }
    public String getOwner() {
        return owner;
    }
    public String getEtnia() {
        return etnia;
    }
    public boolean isLimpio() {
        return limpio;
    }
    public boolean isHaComido() {
        return haComido;
    }
    public boolean isSaltando() {
        return saltando;
    }
    public boolean isGalopando() {
        return galopando;
    }
}
