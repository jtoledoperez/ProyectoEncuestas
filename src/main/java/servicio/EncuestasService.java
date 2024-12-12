package servicio;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import org.hibernate.Session;

import dao.EncuestaDAO;
import dao.UsuarioDAO;
import hibernate.HibernateManager;
import modelo.Encuesta;
import modelo.Rol;
import modelo.Usuario;

public class EncuestasService {

	private EncuestaDAO encuestaDAO;
	private UsuarioDAO usuarioDAO;

	public EncuestasService() {
		this.encuestaDAO = new EncuestaDAO();
		this.usuarioDAO = new UsuarioDAO();
	}

	

	public Integer crearEncuesta(String nombreUsuario, String nombreEncuesta, Date fechaCaducidad) {
	    Usuario usuario = usuarioDAO.getByNombre(nombreUsuario);

	  
	    if (usuario == null) {
	        throw new IllegalArgumentException("Usuario no encontrado.");
	    }

	  
	    if (usuario.getRol() != Rol.CLIENTE) {
	        throw new IllegalArgumentException("Solo los usuarios con rol 'CLIENTE' pueden crear encuestas.");
	    }

	  
	    if (fechaCaducidad == null || fechaCaducidad.before(new Date())) {
	        throw new IllegalArgumentException("La fecha de caducidad no puede ser nula ni anterior a la fecha actual.");
	    }

	   
	    Encuesta nuevaEncuesta = new Encuesta(nombreEncuesta, usuario, fechaCaducidad);
	    encuestaDAO.save(nuevaEncuesta);

	    // Devolvemos el id para crear las preguntas de esta misma encuesta creada
	    return nuevaEncuesta.getIdEncuesta();
	}

	
	public List<Encuesta> listarTodasLasEncuestas() {
		return encuestaDAO.getAll();
	}

	public List<Encuesta> listarEncuestasActivas() {
		List<Encuesta> todasLasEncuestas = encuestaDAO.getAll();


		Date hoy = new Date();
		return todasLasEncuestas.stream()
				.filter(encuesta -> encuesta.getCaducidad() != null && !encuesta.getCaducidad().before(hoy))

				.collect(Collectors.toList());
	}
}
