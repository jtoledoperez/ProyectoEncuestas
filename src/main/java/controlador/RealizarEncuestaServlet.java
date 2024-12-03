package controlador;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Encuesta;
import modelo.Pregunta;
import modelo.Respuesta;
import servicio.EncuestasService;
import servicio.PreguntaService;
import servicio.RespuestaService;

/**
 * Servlet RealizarEncuestaServlet
 * Servlet que muestra las preguntas de una encuesta antes de mostrar listaEncuestas.jsp
 * 
 */
@WebServlet("/realizar-encuesta")
public class RealizarEncuestaServlet extends HttpServlet {

	 private EncuestasService encuestasService;
	 private PreguntaService preguntaService;
	 private RespuestaService respuestaService; 

	 @Override
	 public void init() throws ServletException {
	     encuestasService = new EncuestasService();
	     preguntaService = new PreguntaService();
	     respuestaService = new RespuestaService(); 
	 }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idEncuestaParam = request.getParameter("idEncuesta");
            
            if (idEncuestaParam == null || idEncuestaParam.isEmpty()) {
                request.setAttribute("mensajeError", "No se proporcionó un ID de encuesta válido.");
                request.getRequestDispatcher("listaEncuestas.jsp").forward(request, response);
                return;
            }

            int idEncuesta = Integer.parseInt(idEncuestaParam);

            List<Encuesta> encuestas = encuestasService.listarTodasLasEncuestas();
            
            Encuesta encuestaSeleccionada = encuestas.stream()
                    .filter(encuesta -> encuesta.getIdEncuesta() == idEncuesta)
                    .findFirst()
                    .orElse(null);

            if (encuestaSeleccionada == null) {
                request.setAttribute("mensajeError", "La encuesta seleccionada no existe.");
                request.getRequestDispatcher("listaEncuestas.jsp").forward(request, response);
                return;
            }

            List<Pregunta> preguntas = preguntaService.listarPreguntasDeUnaEncuesta(idEncuesta);

            Map<Pregunta, List<Respuesta>> preguntaRespuestasMap = new LinkedHashMap<>();
            for (Pregunta pregunta : preguntas) {              
                List<Respuesta> respuestas = respuestaService.listarRespuestasDeUnaPregunta(pregunta.getIdPregunta());
                preguntaRespuestasMap.put(pregunta, respuestas);
            }

            request.setAttribute("encuesta", encuestaSeleccionada);
            request.setAttribute("preguntaRespuestasMap", preguntaRespuestasMap);
            request.getRequestDispatcher("realizarEncuesta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Ocurrió un error al cargar la encuesta.");
            request.getRequestDispatcher("listaEncuestas.jsp").forward(request, response);
        }
    }

}