package com.github.pomaretta.cide.service;

import org.hibernate.SessionFactory;

public abstract class ServiceUnit<T> {
    
    protected SessionFactory sessionFactory;

    public ServiceUnit(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public abstract T get(int id);
    public abstract void save(T t);
    public abstract void update(T t);
    public abstract void delete(T t);
    public abstract T[] getAll();

}
