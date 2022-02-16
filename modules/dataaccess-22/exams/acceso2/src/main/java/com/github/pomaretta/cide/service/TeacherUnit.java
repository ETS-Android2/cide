package com.github.pomaretta.cide.service;

import com.github.pomaretta.cide.entity.Teacher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TeacherUnit extends ServiceUnit<Teacher> {

    public TeacherUnit(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Teacher get(int id) {
        Session session = sessionFactory.openSession();
        Teacher person = (Teacher) session.get(Teacher.class, id);
        session.close();
        return person;
    }

    public Teacher[] getByPersonIds(Integer[] personIds) {
        Session session = sessionFactory.openSession();
        Teacher[] persons = (Teacher[]) session.createQuery("from Teacher where personId in (:personIds)")
                .setParameterList("personIds", personIds)
                .list()
                .toArray(new Teacher[0]);
        session.close();
        return persons;
    }

    @Override
    public void save(Teacher t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Teacher t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Teacher t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public Teacher[] getAll() {
        Session session = sessionFactory.openSession();
        Teacher[] persons = (Teacher[]) session.createQuery("from Teacher")
                .list()
                .toArray(new Teacher[0]);
        session.close();
        return persons;
    }
    
}
