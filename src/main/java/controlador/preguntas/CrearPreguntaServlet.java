package controlador.preguntas;

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

@WebServlet("/CrearPreguntasServlet")
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
            // Obtener datos del formulario
            String textoPregunta = request.getParameter("textoPregunta");
            String idEncuestaParam = request.getParameter("idEncuesta");

            // Validación básica
            if (textoPregunta == null || idEncuestaParam == null || textoPregunta.isEmpty()) {
                response.sendRedirect("crearPregunta.jsp?error=Faltan datos");
                return;
            }

            int idEncuesta = Integer.parseInt(idEncuestaParam);

            // Crear la entidad Pregunta
            Pregunta pregunta = new Pregunta();
            pregunta.setTexto(textoPregunta);
            pregunta.setEncuesta(new Encuesta(idEncuesta)); // Relación con Encuesta

            // Guardar la pregunta
            preguntaService.guardarPregunta(pregunta);

            // Crear las respuestas asociadas
            for (int i = 1; i <= 4; i++) {
                String textoRespuesta = request.getParameter("respuesta" + i);
                if (textoRespuesta != null && !textoRespuesta.isEmpty()) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setTexto(textoRespuesta);
                    respuesta.setPregunta(pregunta); // Relación con Pregunta
                    respuestaService.guardarRespuesta(respuesta);
                }
            }

            // Redirigir con éxito
            response.sendRedirect("pruebaCrearPreBoton.jsp?success=true");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("crearPregunta.jsp?error=Error al procesar los datos");
        }
    }
}
