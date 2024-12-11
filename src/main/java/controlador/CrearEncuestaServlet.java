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

        // Validar y convertir la fecha de caducidad
        Date fechaCaducidad = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaCaducidad = dateFormat.parse(caducidadStr);

            if (fechaCaducidad.before(new Date())) {
                request.setAttribute("mensajeError", "La fecha de caducidad no puede ser anterior a la fecha actual.");
                request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
                return;
            }
        } catch (NullPointerException e) {
            request.setAttribute("mensajeError", "La fecha de caducidad es obligatoria.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
            return;
        } catch (ParseException e) {
            request.setAttribute("mensajeError", "Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
            return;
        }
        // Crear la encuesta utilizando el servicio
        String resultado = encuestasService.crearEncuesta(usuario.getNombre(), nombreEncuesta, fechaCaducidad);

        if (resultado.equals("Encuesta creada exitosamente.")) {
            request.setAttribute("mensajeExito", resultado);
            request.getRequestDispatcher("listarEncuestas.jsp").forward(request, response);
        } else {
            request.setAttribute("mensajeError", resultado);
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
        }

    }

}
