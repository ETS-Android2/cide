package com.github.pomaretta.cide.hibernate;

import com.github.pomaretta.cide.hibernate.dto.Albumes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class BaseApp {

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(BaseApp.class.getResource("hibernate.cfg.xml"));

        configuration.addClass(com.github.pomaretta.cide.hibernate.dto.Albumes.class);

        ServiceRegistry serviceRegistry = configuration
                .getStandardServiceRegistryBuilder()
                .applySettings(
                        configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) {

        System.out.println(".......Hibernate Maven Example.......\n");

        Session session = null;

        try {
            // Create session.
            session = buildSessionFactory().openSession();
            session.beginTransaction();
 
            // Create objects.
            for(int i = 101; i <= 105; i++) {
                Albumes album = new Albumes();
                album.setId(i);
                album.setTitulo("Album " + i);
                album.setAutor("Author " + i);
                session.save(album);
            }

            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            session.getTransaction().commit();
        } catch(Exception sqlException) {
            // if(null != session.getTransaction()) {
            //     System.out.println("\n.......Transaction Is Being Rolled Back.......");
            //     session.getTransaction().rollback();
            // }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

    }
}
