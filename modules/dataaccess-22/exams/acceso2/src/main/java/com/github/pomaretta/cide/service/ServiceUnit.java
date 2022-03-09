package com.github.pomaretta.cide.service;

import org.hibernate.SessionFactory;

public abstract class ServiceUnit<T> {
    
    protected SessionFactory sessionFactory;

    public ServiceUnit(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Obtiene un objeto de tipo T a partir de su id.
     * 
     * @param id El id del objeto a obtener.
     * @return El objeto de tipo T.
     */
    public abstract T get(int id);

    /**
     * Guarda un objeto de tipo T en la base de datos.
     * 
     * @param t El objeto de tipo T a guardar.
     */
    public abstract void save(T t);

    /**
     * Modifica un objeto de tipo T en la base de datos.
     * 
     * @param t El objeto de tipo T a modificar.
     */
    public abstract void update(T t);

    /**
     * Borra un objeto de tipo T de la base de datos.
     * 
     * @param t
     */
    public abstract void delete(T t);

    /**
     * Obtiene todos los objetos de tipo T de la base de datos.
     * 
     * @return Un array de objetos de tipo T.
     */
    public abstract T[] getAll();

}
