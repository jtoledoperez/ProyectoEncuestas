package servicio;

import java.util.List;

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

    public String crearEncuesta(String nombreUsuario, String nombreEncuesta) {
        // Buscar usuario por nombre
        Usuario usuario = usuarioDAO.getByNombre(nombreUsuario);
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

    public List<Encuesta> listarTodasLasEncuestas() {
        return encuestaDAO.getAll();
    }
}

