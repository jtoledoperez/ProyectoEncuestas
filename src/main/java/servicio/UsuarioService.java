package servicio;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
    
        this.usuarioDAO = new UsuarioDAO();
    }

   
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);  
    }

   
    public Usuario obtenerUsuario(int id) {
        return usuarioDAO.getById(id); 
    }

  
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = usuarioDAO.getAll();
        // Log de depuración
        System.out.println("Usuarios obtenidos desde el servicio: " + usuarios);
        return usuarios;
    }

    
    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizarUsuario(usuario);  
    }

  
    public void eliminarUsuario(int id) {
        // Obtener el usuario por ID antes de eliminarlo
        Usuario usuario = usuarioDAO.getById(id);
        if (usuario != null) {
            usuarioDAO.delete(usuario);  
        }
    }
}
