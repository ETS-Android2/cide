package com.example.servingwebcontent.representation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private int nota;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota() {
        return nota;
    }

}
