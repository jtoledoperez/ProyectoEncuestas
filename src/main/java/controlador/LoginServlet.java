package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Si el usuario ya está logueado, redirigirlo a la página principal
        HttpSession session = request.getSession();
        if (session.getAttribute("usuario") != null) {
            response.sendRedirect("bienvenido.jsp"); 
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response); 
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cogemos los datos
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validamos los campos del formulario
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Ambos campos son obligatorios.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

       
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Buscamos por el email
            Usuario usuario = usuarioDAO.getByEmail(email);

            // Verificar si el usuario existe y si la contraseña es correcta
            if (usuario != null && BCrypt.checkpw(password, usuario.getPassword())) {
                // Si la contraseña es correcta, iniciar sesión
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario); 
                response.sendRedirect("bienvenido.jsp"); 
            } else {
                // Si las credenciales no son correctas
                request.setAttribute("error", "Correo o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al intentar iniciar sesión.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
