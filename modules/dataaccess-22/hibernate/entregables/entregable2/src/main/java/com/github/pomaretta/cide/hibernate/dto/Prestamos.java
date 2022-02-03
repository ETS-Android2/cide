package com.github.pomaretta.cide.hibernate.dto;

import java.sql.Date;

public class Prestamos {
    private int idLibro;
    private int idSocio;
    private Date inicio;
    private Date fin;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prestamos prestamos = (Prestamos) o;

        if (idLibro != prestamos.idLibro) return false;
        if (idSocio != prestamos.idSocio) return false;
        if (inicio != null ? !inicio.equals(prestamos.inicio) : prestamos.inicio != null) return false;
        if (fin != null ? !fin.equals(prestamos.fin) : prestamos.fin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLibro;
        result = 31 * result + idSocio;
        result = 31 * result + (inicio != null ? inicio.hashCode() : 0);
        result = 31 * result + (fin != null ? fin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Prestamos{" +
                "idLibro=" + idLibro +
                ", idSocio=" + idSocio +
                ", inicio=" + inicio +
                ", fin=" + fin +
                '}';
    }

}
