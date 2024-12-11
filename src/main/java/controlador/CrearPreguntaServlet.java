package controlador;

import modelo.Pregunta;
import modelo.Respuesta;
import modelo.Encuesta;
import servicio.PreguntaService;
import servicio.RespuestaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet CrearPreguntaServlet
 * servlet que maneja la petición post para procesar las preguntas y respuestas del formulario y crear los registros en la base de datos
 * 
 */
@WebServlet("/crear-pregunta")
public class CrearPreguntaServlet extends HttpServlet {

    private PreguntaService preguntaService;
    private RespuestaService respuestaService;

    @Override
    public void init() throws ServletException {
        super.init();
        preguntaService = new PreguntaService();
        respuestaService = new RespuestaService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // validar que se haya pasado un id de encuesta
            String idEncuestaParam = request.getParameter("idEncuesta");
            if (idEncuestaParam == null || idEncuestaParam.isEmpty()) {
                request.setAttribute("error", "Por favor cree una encuesta primero.");
                request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
                return;
            }          
            int idEncuesta = Integer.parseInt(idEncuestaParam);           
            // validar el texto de la pregunta
            String textoPregunta = request.getParameter("pregunta");
            if (textoPregunta == null || textoPregunta.trim().isEmpty()) {
                request.setAttribute("error", "El campo de pregunta no puede estar vacío.");
                request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
                return;
            }
            // validar el texto de las respuestas
            for (int i = 1; i <= 4; i++) {
                String textoRespuesta = request.getParameter("respuesta" + i);
                if (textoRespuesta == null || textoRespuesta.trim().isEmpty()) {
                    request.setAttribute("error", "Las preguntas tienen que tener 4 respuestas. Por favor, rellene todas.");
                    request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
                    return;
                }
            }
            // crear pregunta
            Pregunta pregunta = new Pregunta();
            pregunta.setTexto(textoPregunta);
            pregunta.setEncuesta(new Encuesta(idEncuesta));
            preguntaService.guardarPregunta(pregunta);
            // guardar las respuestas
            for (int i = 1; i <= 4; i++) {
                String textoRespuesta = request.getParameter("respuesta" + i);
                Respuesta respuesta = new Respuesta();
                respuesta.setTexto(textoRespuesta);
                respuesta.setPregunta(pregunta);
                respuestaService.guardarRespuesta(respuesta);
            }     
            response.sendRedirect("crear-encuesta");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado al procesar los datos.");
            request.getRequestDispatcher("crearEncuesta.jsp").forward(request, response);
        }
    }
}
