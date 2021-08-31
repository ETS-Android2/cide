package com.example.servingwebcontent.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private String nombre;
    private int edad;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Asignatura> asignaturas;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

}