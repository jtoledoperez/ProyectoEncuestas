package controlador.preguntas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BotonCrearPreguntaServlet")
public class BotonCrearPreguntaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoge el ID de la encuesta
        String idEncuesta = request.getParameter("idEncuesta");
        
        // Pasa el ID a la vista de creación
        request.setAttribute("idEncuesta", idEncuesta);
        request.getRequestDispatcher("crearPregunta.jsp").forward(request, response);
    }
}
