package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EncuestaDAO;
import modelo.Encuesta;
import modelo.Rol;
import modelo.Usuario;

/**
 * Servlet implementation class CrearEncuestaServlet
 */
@WebServlet("/crear-encuesta")

public class CrearEncuestaServlet extends HttpServlet {

    private EncuestaDAO encuestaDAO = new EncuestaDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("crearEncuesta.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Verificar si el usuario está autenticado
        if (usuario == null) {
            response.sendRedirect("login.jsp");  
            return;
        }

        // Verificar si el usuario tiene el rol de CLIENTE
        if (usuario.getRol() != Rol.CLIENTE) {
            request.setAttribute("mensajeError", "Solo los usuarios con rol 'CLIENTE' pueden crear encuestas.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
            return;
        }

        // Crear la encuesta
        String nombreEncuesta = request.getParameter("nombreEncuesta");
        Encuesta encuesta = new Encuesta(nombreEncuesta, usuario);
        encuestaDAO.save(encuesta);

        request.setAttribute("idEncuesta", encuesta.getIdEncuesta());
        
        // Confirmar la creación de la encuesta
        request.setAttribute("mensajeExito", "Encuesta creada exitosamente.");
        request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
    }
}

