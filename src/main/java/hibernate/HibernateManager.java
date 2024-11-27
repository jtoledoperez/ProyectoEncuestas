package hibernate;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateManager {

    private static SessionFactory sessionFactory;

    private static SessionFactory configureSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(); // Lee el archivo hibernate.cfg.xml

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null)
            sessionFactory = configureSessionFactory();
        return sessionFactory;
    }
}
