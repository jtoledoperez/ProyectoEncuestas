package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import modelo.Usuario;

import org.hibernate.*;
//Fijate que lo importamos para poder realizar la configuracion debajo
import org.hibernate.cfg.Configuration;

/**
 * Proporciona un objeto de la clase SessionFactory para ser utilizado con
 * Hibernate
 */
public class HibernateManager {

	// Creamos al instancia
	private static SessionFactory sessionFactory;

	public static SessionFactory configureSessionFactory() throws HibernateException {
	    // Creamos la configuración
	    Configuration configuration = new Configuration();
	    configuration.configure();  // Esto lee el archivo hibernate.cfg.xml
	    configuration.addAnnotatedClass(Usuario.class);  // Aseguramos que la clase Usuario esté registrada
	    sessionFactory = configuration.buildSessionFactory();

	    return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = configureSessionFactory();
		return sessionFactory;
	}

}
