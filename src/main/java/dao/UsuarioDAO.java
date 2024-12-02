package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernate.HibernateManager;
import modelo.Usuario;

import java.util.List;

public class UsuarioDAO {

	// Guardar un usuario
	public void save(Usuario usuario) {
		try (Session session = HibernateManager.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			try {
				session.save(usuario);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				System.err.println("Error guardando el usuario: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("Error al abrir la sesión de Hibernate: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public Usuario getByEmail(String email) {
	    try (Session session = HibernateManager.getSessionFactory().openSession()) {
	        Query<Usuario> query = session.createQuery("FROM Usuario WHERE email = :email", Usuario.class);
	        query.setParameter("email", email);
	        return query.uniqueResult();
	    } catch (Exception e) {
	        System.err.println("Error obteniendo usuario por email: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
	 public Usuario getByNombre(String nombre) {
	        try (Session session = HibernateManager.getSessionFactory().openSession()) {
	            Query<Usuario> query = session.createQuery("FROM Usuario WHERE nombre = :nombre", Usuario.class);
	            query.setParameter("nombre", nombre);
	            return query.uniqueResult(); 
	        } catch (Exception e) {
	            System.err.println("Error obteniendo usuario por nombre: " + e.getMessage());
	            e.printStackTrace();
	            return null;
	        }
	    }
	// Verificar si un usuario con un correo electrónico ya existe
	public boolean existsByEmail(String email) {
		try (Session session = HibernateManager.getSessionFactory().openSession()) {
			String hql = "FROM Usuario WHERE email = :email";
			Query<Usuario> query = session.createQuery(hql, Usuario.class);
			query.setParameter("email", email);
			List<Usuario> result = query.list();
			return !result.isEmpty();
		} catch (Exception e) {
			System.err.println("Error verificando el email del usuario: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// Obtener un usuario por su ID
	public Usuario getById(int id) {
		try (Session session = HibernateManager.getSessionFactory().openSession()) {
			return session.get(Usuario.class, id);
		} catch (Exception e) {
			System.err.println("Error obteniendo el usuario por ID: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	// Obtener todos los usuarios
	public List<Usuario> getAll() {
		try (Session session = HibernateManager.getSessionFactory().openSession()) {
		
			Query<Usuario> query = session.createQuery("FROM Usuario", Usuario.class);
			return query.list();
		} catch (Exception e) {
			System.err.println("Error obteniendo los usuarios: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	// Actualizar un usuario
	public boolean actualizarUsuario(Usuario usuario) {
		try (Session session = HibernateManager.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			try {
				Usuario existe = session.get(Usuario.class, usuario.getIdUsuario());
				if (existe != null) {
					existe.setNombre(usuario.getNombre());
					existe.setApellidos(usuario.getApellidos());
					existe.setPassword(usuario.getPassword());
					existe.setEmail(usuario.getEmail());
					existe.setRol(usuario.getRol());
					session.update(existe);
					transaction.commit();
					return true;
				} else {
					transaction.rollback();
					return false;
				}
			} catch (Exception e) {
				transaction.rollback();
				System.err.println("Error actualizando el usuario: " + e.getMessage());
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			System.err.println("Error abriendo la sesión de Hibernate: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// Eliminar un usuario
	public void delete(Usuario usuario) {
		try (Session session = HibernateManager.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			try {
				session.delete(usuario);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				System.err.println("Error eliminando el usuario: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("Error al abrir la sesión de Hibernate para eliminar el usuario: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
