package com.github.pomaretta.cide.service;

import com.github.pomaretta.cide.entity.Department;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DepartmentUnit extends ServiceUnit<Department> {

    public DepartmentUnit(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Department get(int id) {
        Session session = sessionFactory.openSession();
        Department department = (Department) session.get(Department.class, id);
        session.close();
        return department;
    }

    public Department[] getByName(String name) {
        Session session = sessionFactory.openSession();
        Department[] departments = (Department[]) session.createQuery("from Department where name LIKE :name")
                .setParameter("name", name).list().toArray(new Department[0]);
        session.close();
        return departments;
    }

    @Override
    public void save(Department t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Department t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Department t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Department[] getAll() {
        Session session = sessionFactory.openSession();
        Department[] departments = (Department[]) session.createQuery("from Department").list().toArray(new Department[0]);
        session.close();
        return departments;
    }

}
