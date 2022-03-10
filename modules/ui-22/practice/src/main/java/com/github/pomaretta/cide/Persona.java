package com.github.pomaretta.cide;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer edad;

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return String.format(
            "Persona [nombre=%s, apellido=%s, edad=%s]",
            nombre,
            apellido,
            edad
        );
    }

}