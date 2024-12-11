package controlador;

import java.io.IOException;
import java.util.Date;
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
            Date fechaActual = new Date();

            // Añadimos la validación de caducidad
            for (Encuesta encuesta : encuestas) {
                if (encuesta.getCaducidad() != null && encuesta.getCaducidad().before(fechaActual)) {
                	// Indicamos que la encuesta está caducada
                    encuesta.setCaducada(true); 
                } else {
                    encuesta.setCaducada(false);
                }
            }

            request.setAttribute("encuestas", encuestas);
            request.getRequestDispatcher("/encuestasDisponibles.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error al cargar las encuestas.");
            request.getRequestDispatcher("/encuestasDisponibles.jsp").forward(request, response);
        }
    }
}
