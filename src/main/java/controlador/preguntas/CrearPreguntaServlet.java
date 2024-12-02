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
/**
 * Servlet CrearPreguntaServlet
 * servlet que maneja la petición post para procesar las preguntas y respuestas del formulario y crear los registros en la base de datos
 * 
 */
@WebServlet("/CrearPreguntasServlet")
public class CrearPreguntaServlet extends HttpServlet {
	// instancias de los servicios de pregunta y respuesta
	private PreguntaService preguntaService;
	private RespuestaService respuestaService;
	
	// método init para inicializar los servicios
	@Override
	public void init() throws ServletException {
		super.init();
		preguntaService = new PreguntaService();
		respuestaService = new RespuestaService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// obtener el id de la encuesta del campo oculto
			String idEncuestaString = request.getParameter("idEncuesta");
			// parsear el id de encuesta de string a int
			int idEncuesta = Integer.parseInt(idEncuestaString);
			// instancia de pregunta
			Pregunta pregunta = new Pregunta();

			// validar que el id de la encuesta existe
			if (idEncuestaString == null || idEncuestaString.isEmpty()) {
				response.sendRedirect("crearPregunta.jsp?error=Faltan datos");
				return;
			}
			
			// iterar sobre las preguntas
			for (int i = 1; request.getParameter("pre" + i + "textoPregunta") != null; i++) {
				// obtener el texto de la pregunta
				String textoPregunta = request.getParameter("pre" + i + "textoPregunta");

				// validar que el texto de la pregunta no esté vacío o sea nulo
				if (textoPregunta == null || textoPregunta.isEmpty()) {
					response.sendRedirect("crearPregunta.jsp?error=Faltan datos en alguna pregunta");
					return;
				}
				
				// añadir datos a la pregunta
				pregunta.setTexto(textoPregunta);
				pregunta.setEncuesta(new Encuesta(idEncuesta)); 

				// guardar la pregunta
				preguntaService.guardarPregunta(pregunta);

				// iterar sobre las respuestas para crearlas en la base de datos
				for (int j = 1; j <= 4; j++) {
					// obtener el texto de la respuesta
					String textoRespuesta = request.getParameter("pre" + i + "res" + j);
					// validar que el texto de la respuesta no sea nulo o esté vacío
					if (textoRespuesta != null && !textoRespuesta.isEmpty()) {
						// crear las instancias de respuesta
						Respuesta respuesta = new Respuesta();
						// añadir datos a la respuesta
						respuesta.setTexto(textoRespuesta);
						respuesta.setPregunta(pregunta); 

						// guardar la respuesta
						respuestaService.guardarRespuesta(respuesta);
					} 
				}
			}

			// redirección
			response.sendRedirect("pruebaCrearPreBoton.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("crearPregunta.jsp?error=Error al procesar los datos");
		}
	}
}
