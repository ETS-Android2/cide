package com.github.pomaretta.cide.service;

import com.github.pomaretta.cide.entity.Department;
import com.github.pomaretta.cide.entity.Person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersonUnit extends ServiceUnit<Person> {
    
    public PersonUnit(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person get(int id) {
        Session session = sessionFactory.openSession();
        Person person = (Person) session.get(Person.class, id);
        session.close();
        return person;
    }

    public Person[] getByName(String name) {
        Session session = sessionFactory.openSession();
        Person[] persons = (Person[]) session.createQuery("from Person where name LIKE :name")
                .setParameter("name", name)
                .list()
                .toArray(new Person[0]);
        session.close();
        // Sort the array
        java.util.Arrays.sort(persons);
        return persons;
    }

    public Person getByNif(String nif) {
        Session session = sessionFactory.openSession();
        Person person = (Person) session.createQuery("from Person where nif = :nif")
                .setParameter("nif", nif)
                .uniqueResult();
        session.close();
        return person;
    }

    public Person[] getByFirstLastName(String fString) {
        Session session = sessionFactory.openSession();
        Person[] persons = (Person[]) session.createQuery("from Person where firstLastname LIKE :firstLastname")
                .setParameter("firstLastname", fString)
                .list()
                .toArray(new Person[0]);
        session.close();
        // Sort the array
        java.util.Arrays.sort(persons);
        return persons;
    }

    public Person[] getBySecondLastName(String sString) {
        Session session = sessionFactory.openSession();
        Person[] persons = (Person[]) session.createQuery("from Person where secondLastname LIKE :secondLastname")
                .setParameter("secondLastname", sString)
                .list()
                .toArray(new Person[0]);
        session.close();
        // Sort the array
        java.util.Arrays.sort(persons);
        return persons;
    }

    public Person[] getPersonsByDepartment(Department department) {
        Session session = sessionFactory.openSession();
        // Persons that are teachers and get department id
        Person[] persons = (Person[]) session.createQuery("from Person where id in (select personId from Teacher where departmentId = :departmentId)")
                .setParameter("departmentId", department.getId())
                .list()
                .toArray(new Person[0]);
        session.close();
        // Sort the array
        java.util.Arrays.sort(persons);
        return persons;
    }
    
    @Override
    public void save(Person t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Person t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Person t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Person[] getAll() {
        Session session = sessionFactory.openSession();
        Person[] persons = (Person[]) session.createQuery("from Person").list().toArray(new Person[0]);
        session.close();
        // Sort the array
        java.util.Arrays.sort(persons);
        return persons;
    }

}
