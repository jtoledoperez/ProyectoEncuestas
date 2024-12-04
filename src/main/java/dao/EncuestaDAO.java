package dao;

import hibernate.HibernateManager;
import modelo.Encuesta;
import modelo.Rol;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class EncuestaDAO {

	// Guardar una encuesta
	public void save(Encuesta encuesta) {
		try (Session session = HibernateManager.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			try {
				// Validar rol del usuario
				if (encuesta.getUsuario().getRol() != Rol.CLIENTE) {
					System.err.println("El usuario no tiene permisos para crear una encuesta.");
					return;
				}

				// Validar fecha de caducidad
				if (encuesta.getCaducidad() != null && encuesta.getCaducidad().before(new Date())) {
					System.err.println("La fecha de caducidad no puede ser anterior a la fecha actual.");
					return;
				}

				// Guardar la encuesta si las validaciones son correctas
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
			Query<Encuesta> query = session.createQuery("FROM Encuesta e JOIN FETCH e.usuario", Encuesta.class);
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
				// Validar fecha de caducidad al actualizar
				if (encuesta.getCaducidad() != null && encuesta.getCaducidad().before(new Date())) {
					System.err.println("La fecha de caducidad no puede ser anterior a la fecha actual.");
					return false;
				}

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
	//Vemos las encuestas que están activas comprobando la fecha
	public List<Encuesta> getAllActivas() {
	    try (Session session = HibernateManager.getSessionFactory().openSession()) {
	        Query<Encuesta> query = session.createQuery(
	            "FROM Encuesta e JOIN FETCH e.usuario WHERE e.caducidad >= :hoy", Encuesta.class);
	        query.setParameter("hoy", new Date());
	        return query.list();
	    } catch (Exception e) {
	        System.err.println("Error obteniendo las encuestas activas: " + e.getMessage());
	        e.printStackTrace();
	        return null;
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
