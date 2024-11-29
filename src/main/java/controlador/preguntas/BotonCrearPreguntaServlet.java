package controlador.preguntas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet BotonCrearPreguntaServlet
 * servlet que maneja la petición get para mandar el id de la encuesta y redirigir a crearPregunta.jsp
 * 
 */
@WebServlet("/BotonCrearPreguntaServlet")
public class BotonCrearPreguntaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recoger el id de la encuesta desde el fomulario
        String idEncuesta = request.getParameter("idEncuesta");
        
        // almacenar idEncuesta en el objeto HttpServletRequest para que esté accesible en crearPregunta.jsp
        request.setAttribute("idEncuesta", idEncuesta);
        
        // redirigir a crearPregunta.jsp
        request.getRequestDispatcher("crearPregunta.jsp").forward(request, response);
    }
}
