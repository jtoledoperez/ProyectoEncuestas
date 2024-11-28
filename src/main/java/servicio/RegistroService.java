package servicio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.UsuarioDAO;
import modelo.Usuario;
import modelo.Rol; 
import org.mindrot.jbcrypt.BCrypt;

public class RegistroService {

    private static final Logger logger = LogManager.getLogger(RegistroService.class);
    private final UsuarioDAO usuarioDAO;

    public RegistroService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

  
    public String registrarUsuario(String email, String clave, String nombre, String apellidos, Rol rol) {
        // Validar si el email ya está registrado
        if (usuarioDAO.existsByEmail(email)) {
            logger.warn("El correo electrónico {} ya está registrado.", email);
            return "El correo electrónico ya está registrado.";
        }

        // Validar si la contraseña cumple con los requisitos mínimos
        if (clave == null || clave.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }

        // Encriptar la contraseña
        String hashedClave = BCrypt.hashpw(clave, BCrypt.gensalt());

        // Crear y configurar un nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(hashedClave);  
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellidos(apellidos);
        nuevoUsuario.setRol(rol);

        // Guardar el usuario usando el DAO
        try {
            usuarioDAO.save(nuevoUsuario);
            logger.info("Nuevo usuario registrado con el correo electrónico: {}", email);
        } catch (Exception e) {
            logger.error("Error al registrar el usuario: {}", e.getMessage(), e);
            return "Error al registrar el usuario. Inténtalo nuevamente.";
        }

        return null; 
    }
}
