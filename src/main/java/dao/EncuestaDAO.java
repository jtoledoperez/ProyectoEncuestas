package dao;

import hibernate.HibernateManager;
import modelo.Encuesta;
import modelo.Rol;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EncuestaDAO {

    // Guardar una encuesta
	public void save(Encuesta encuesta) {
	    try (Session session = HibernateManager.getSessionFactory().openSession()) {
	        Transaction transaction = session.beginTransaction();
	        try {
	            // Verificar que el rol del usuario sea CLIENTE
	            if (encuesta.getUsuario().getRol() != Rol.CLIENTE) {
	                System.err.println("El usuario no tiene permisos para crear una encuesta.");
	                return; 
	            }
	            
	            // Guardar la encuesta si el usuario tiene el rol correcto
	            session.save(encuesta);
	            transaction.commit();
	        } catch (Exception e) {
	            transaction.rollback();
	            System.err.println("Error guardando la encuesta: " + e.getMessage());
	            e.printStackTrace();
	        }
	    } catch (Exception e) {
	        System.err.println("Error abriendo la sesión de Hibernate: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


    // Obtener una encuesta por su ID
    public Encuesta getById(int id) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            return session.get(Encuesta.class, id);
        } catch (Exception e) {
            System.err.println("Error obteniendo la encuesta por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Obtener todas las encuestas
    public List<Encuesta> getAll() {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Query<Encuesta> query = session.createQuery("FROM Encuesta", Encuesta.class);
            return query.list();
        } catch (Exception e) {
            System.err.println("Error obteniendo las encuestas: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Actualizar una encuesta
    public boolean update(Encuesta encuesta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(encuesta);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error actualizando la encuesta: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    // Eliminar una encuesta
    public void delete(Encuesta encuesta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(encuesta);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error eliminando la encuesta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
