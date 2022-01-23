package com.github.pomaretta.cide.hibernate.dto;

public class Albumes implements java.io.Serializable {
    
    private int id;
    private String titulo;
    private String autor;

    public Albumes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
