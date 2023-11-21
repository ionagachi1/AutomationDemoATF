package utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFrameworkSetup {
    private static SessionFactory sessionFactory;
    private HibernateFrameworkSetup() {
        // Private constructor to prevent direct instantiation
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // Load Hibernate configuration from hibernate.cfg.xml
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            // Build the SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}