package controlador;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Encuesta;
import modelo.Pregunta;
import servicio.EncuestasService;
import servicio.PreguntaService;

/**
 * Servlet ListarEncuestasDisponiblesServlet
 * Servlet que filtra las encuestas antes de mostrar encuestasDisponibles.jsp
 * 
 */
@WebServlet("/listar-encuestas-disponibles")
public class ListarEncuestasDisponiblesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EncuestasService encuestasService;
    private PreguntaService preguntaService;

    @Override
    public void init() throws ServletException {
        encuestasService = new EncuestasService();
        preguntaService = new PreguntaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Encuesta> encuestas = encuestasService.listarTodasLasEncuestas();

            // filtrar poe encuestas que tienen al menos una pregunta
            List<Encuesta> encuestasConPreguntas = encuestas.stream()
                    .filter(encuesta -> {
                        List<Pregunta> preguntas = preguntaService.listarPreguntasDeUnaEncuesta(encuesta.getIdEncuesta());
                        return preguntas != null && !preguntas.isEmpty();
                    })
                    .collect(Collectors.toList());

            // verificar si hay encuestas disponibles
            if (encuestasConPreguntas.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron encuestas disponibles con preguntas.");
            } else {
                request.setAttribute("encuestas", encuestasConPreguntas);
            }

            request.getRequestDispatcher("/encuestasDisponibles.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error al cargar las encuestas.");
            request.getRequestDispatcher("/encuestasDisponibles.jsp").forward(request, response);
        }
    }
}