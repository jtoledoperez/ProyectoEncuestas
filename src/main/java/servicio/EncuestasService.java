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

	// Método para crear una encuesta con validación de fecha de caducidad

	public String crearEncuesta(String nombreUsuario, String nombreEncuesta, Date fechaCaducidad) {

		Usuario usuario = usuarioDAO.getByNombre(nombreUsuario);
		if (usuario == null) {
			return "Usuario no encontrado.";
		}


		if (usuario.getRol() != Rol.CLIENTE) {
			return "Solo los usuarios con rol 'CLIENTE' pueden crear encuestas.";
		}


		// Validar la fecha de caducidad
		if (fechaCaducidad == null || fechaCaducidad.before(new Date())) {
			return "La fecha de caducidad no puede ser nula ni anterior a la fecha actual.";
		}


		// Crear y guardar la encuesta
		Encuesta nuevaEncuesta = new Encuesta(nombreEncuesta, usuario, fechaCaducidad);
		encuestaDAO.save(nuevaEncuesta);

		return "Encuesta creada exitosamente.";
	}

	// Método para listar todas las encuestas
	public List<Encuesta> listarTodasLasEncuestas() {
		return encuestaDAO.getAll();
	}

	public List<Encuesta> listarEncuestasActivas() {
		List<Encuesta> todasLasEncuestas = encuestaDAO.getAll();


		// Filtrar encuestas no caducadas
		Date hoy = new Date();
		return todasLasEncuestas.stream()
				.filter(encuesta -> encuesta.getCaducidad() != null && !encuesta.getCaducidad().before(hoy))

				.collect(Collectors.toList());
	}
}
