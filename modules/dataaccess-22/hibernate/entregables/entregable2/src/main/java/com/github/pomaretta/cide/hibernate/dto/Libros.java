package com.github.pomaretta.cide.hibernate.dto;

public class Libros {

    private int idLibro;
    private String titulo;
    private int ejemplares;
    private String editorial;
    private int paginas;
    private int edicion;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Libros libros = (Libros) o;

        if (idLibro != libros.idLibro) return false;
        if (ejemplares != libros.ejemplares) return false;
        if (paginas != libros.paginas) return false;
        if (edicion != libros.edicion) return false;
        if (titulo != null ? !titulo.equals(libros.titulo) : libros.titulo != null) return false;
        if (editorial != null ? !editorial.equals(libros.editorial) : libros.editorial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLibro;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + ejemplares;
        result = 31 * result + (editorial != null ? editorial.hashCode() : 0);
        result = 31 * result + paginas;
        result = 31 * result + edicion;
        return result;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", ejemplares=" + ejemplares +
                ", editorial='" + editorial + '\'' +
                ", paginas=" + paginas +
                ", edicion=" + edicion +
                '}';
    }

}
