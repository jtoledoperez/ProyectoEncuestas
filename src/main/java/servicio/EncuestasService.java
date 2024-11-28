package servicio;

import dao.EncuestaDAO;
import dao.UsuarioDAO;
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

	public String crearEncuesta(int idUsuario, String nombreEncuesta) {
		Usuario usuario = usuarioDAO.getById(idUsuario);
		if (usuario == null) {
			return "Usuario no encontrado.";
		}

		if (usuario.getRol() != Rol.CLIENTE) {
			return "Solo los usuarios con rol 'CLIENTE' pueden crear encuestas.";
		}

		Encuesta nuevaEncuesta = new Encuesta(nombreEncuesta, usuario);
		encuestaDAO.save(nuevaEncuesta);
		return "Encuesta creada exitosamente.";
	}

}