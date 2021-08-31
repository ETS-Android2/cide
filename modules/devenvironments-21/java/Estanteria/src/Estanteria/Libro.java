package Estanteria;

/*

    Project     ED21
    Package     Estanteria    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-04

    DESCRIPTION
    
*/

import java.util.Objects;

/**
 * @author Carlos Pomares
 */

public class Libro {

    private String titulo;
    private String autor;
    private float calificacion;

    public Libro(String titulo, String autor, float calificacion){
        this.titulo = titulo;
        this.autor = autor;
        this.calificacion = calificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public static int comparate(Libro e,Libro e1){
        return Float.compare(e.getCalificacion(), e1.getCalificacion());
    }

}
