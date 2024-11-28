package dao;

import hibernate.HibernateManager;
import modelo.Pregunta;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PreguntaDAO {

    // Guardar una pregunta
    public void save(Pregunta pregunta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(pregunta);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error guardando la pregunta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Obtener una pregunta por su ID
    public Pregunta getById(int id) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            return session.get(Pregunta.class, id);
        } catch (Exception e) {
            System.err.println("Error obteniendo la pregunta por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Obtener todas las preguntas
    public List<Pregunta> getAll() {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Query<Pregunta> query = session.createQuery("FROM Pregunta", Pregunta.class);
            return query.list();
        } catch (Exception e) {
            System.err.println("Error obteniendo las preguntas: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Actualizar una pregunta
    public boolean update(Pregunta pregunta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(pregunta);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error actualizando la pregunta: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    // Eliminar una pregunta
    public void delete(Pregunta pregunta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(pregunta);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Error eliminando la pregunta: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
