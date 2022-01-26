package com.github.pomaretta.cide.hibernate;

import java.util.logging.Logger;

import com.github.pomaretta.cide.hibernate.dto.UserFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class BaseApp {

    public static final Logger logger = Logger.getLogger(BaseApp.class.getName());

    private SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(BaseApp.class.getResource("hibernate.cfg.xml"));

        ServiceRegistry serviceRegistry = configuration
                .getStandardServiceRegistryBuilder()
                .applySettings(
                        configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) throws Exception {

        BaseApp app = new BaseApp();

        BaseApp.logger.info("Building session factory");
        Session session = app.buildSessionFactory().openSession();;

        BaseApp.logger.info("Beginning transaction");
        session.beginTransaction();

        for (int i = 1; i <= 5; i++) {
            BaseApp.logger.info("Creating user " + i);
            session.save(
                new UserFactory()
                .setId(i)
                .setUsername("admin" + i)
                .setPassword("admin" + i)
                .createUser()
            );
        }

        BaseApp.logger.info("Committing transaction");
        session.getTransaction().commit();
        session.close();
    }
}
