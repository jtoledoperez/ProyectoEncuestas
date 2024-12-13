package controlador;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servicio.EncuestasService;
import modelo.Rol;
import modelo.Usuario;

/**
 * 
 * Servlet implementation class CrearEncuestaServlet
 * 
 */
@WebServlet("/crear-encuesta")
public class CrearEncuestaServlet extends HttpServlet {


    private EncuestasService encuestasService = new EncuestasService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");   
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }        
        if (usuario.getRol() != Rol.CLIENTE) {
            request.setAttribute("mensajeError", "Solo los usuarios con rol 'CLIENTE' pueden crear encuestas.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
            return;

        }

        // Obtener parámetros del formulario
        String nombreEncuesta = request.getParameter("nombreEncuesta");
        String caducidadStr = request.getParameter("fechaCaducidad");

        // Validar nombre de la encuesta
        if (nombreEncuesta == null || nombreEncuesta.trim().isEmpty() || nombreEncuesta.length() < 8) {
            request.setAttribute("mensajeError", "El nombre debe contener al menos 8 caracteres y no puede empezar por un espacio.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
            return;
        }

        // Validar la fecha de caducidad
        Date fechaCaducidad = null;
        if (caducidadStr == null || caducidadStr.trim().isEmpty()) {
            request.setAttribute("mensajeError", "La fecha de caducidad es obligatoria.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); 
            fechaCaducidad = sdf.parse(caducidadStr);
        } catch (ParseException e) {
            request.setAttribute("mensajeError", "Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
            return;
        }

        // Crear la encuesta utilizando el servicio
        try {
            int idEncuesta = encuestasService.crearEncuesta(usuario.getIdUsuario(), nombreEncuesta, fechaCaducidad);

            // Guardar el ID en la sesión
            session.setAttribute("idEncuesta", idEncuesta);
            session.setAttribute("nombreEncuesta", nombreEncuesta);

            // Redirigir a la página de creación de preguntas
            response.sendRedirect("crearEncuesta.jsp");
        } catch (IllegalArgumentException e) {
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Ocurrió un error inesperado.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
        }
    }
}
