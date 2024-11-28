package dao;

import hibernate.HibernateManager;
import modelo.Respuesta;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RespuestaDAO {

    // Guardar una respuesta
    public void save(Respuesta respuesta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(respuesta);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error guardando la respuesta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Obtener una respuesta por su ID
    public Respuesta getById(int id) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            return session.get(Respuesta.class, id);
        } catch (Exception e) {
            System.err.println("Error obteniendo la respuesta por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Obtener todas las respuestas
    public List<Respuesta> getAll() {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Query<Respuesta> query = session.createQuery("FROM Respuesta", Respuesta.class);
            return query.list();
        } catch (Exception e) {
            System.err.println("Error obteniendo las respuestas: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Actualizar una respuesta
    public boolean update(Respuesta respuesta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(respuesta);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error actualizando la respuesta: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    // Eliminar una respuesta
    public void delete(Respuesta respuesta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(respuesta);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error eliminando la respuesta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
