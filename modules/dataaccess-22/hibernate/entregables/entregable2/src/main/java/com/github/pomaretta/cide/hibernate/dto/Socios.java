package com.github.pomaretta.cide.hibernate.dto;

public class Socios {

    private int idSocio;    
    private String nombre;
    private String apellidos;
    private int edad;
    private String direccion;
    private String telefono;

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Socios socios = (Socios) o;

        if (idSocio != socios.idSocio) return false;
        if (edad != socios.edad) return false;
        if (nombre != null ? !nombre.equals(socios.nombre) : socios.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(socios.apellidos) : socios.apellidos != null) return false;
        if (direccion != null ? !direccion.equals(socios.direccion) : socios.direccion != null) return false;
        if (telefono != null ? !telefono.equals(socios.telefono) : socios.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSocio;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + edad;
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Socios{" +
                "idSocio=" + idSocio +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

}
