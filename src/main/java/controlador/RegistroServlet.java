package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicio.RegistroService; 
import dao.UsuarioDAO; 
import modelo.Rol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(RegistroServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getRequestDispatcher("registro.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (nombre == null || apellidos == null || email == null || password == null ||
        		  nombre.trim().isEmpty() || apellidos.trim().isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }       
        UsuarioDAO usuarioDAO = new UsuarioDAO(); 
        RegistroService registroService = new RegistroService(usuarioDAO);       
        String resultado = registroService.registrarUsuario(email, password, nombre, apellidos, Rol.CLIENTE);        
        if (resultado != null) {            
            request.setAttribute("error", resultado);
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        } else {            
            request.setAttribute("success", "Usuario registrado exitosamente.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
