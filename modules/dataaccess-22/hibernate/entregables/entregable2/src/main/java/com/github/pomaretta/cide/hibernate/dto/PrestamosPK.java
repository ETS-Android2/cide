package com.github.pomaretta.cide.hibernate.dto;

import java.io.Serializable;

public class PrestamosPK implements Serializable {
    private int idLibro;
    private int idSocio;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrestamosPK that = (PrestamosPK) o;

        if (idLibro != that.idLibro) return false;
        if (idSocio != that.idSocio) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLibro;
        result = 31 * result + idSocio;
        return result;
    }
}
