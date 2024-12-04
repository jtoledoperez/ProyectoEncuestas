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

    // Método para listar todas las encuestas
    public List<Encuesta> listarTodasLasEncuestas() {
        System.out.println("Recuperando todas las encuestas...");

        // Llamamos al DAO para obtener las encuestas
        List<Encuesta> encuestas = encuestaDAO.getAll();

        // Verificamos si se han encontrado encuestas
        if (encuestas == null || encuestas.isEmpty()) {
            System.out.println("No se han encontrado encuestas.");
        } else {
            System.out.println("Se han encontrado " + encuestas.size() + " encuestas.");
        }

        return encuestas;
    }
}


