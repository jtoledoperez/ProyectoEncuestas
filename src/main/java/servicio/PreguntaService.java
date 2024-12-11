package servicio;

import java.util.List;
import java.util.stream.Collectors;

import dao.PreguntaDAO;
import modelo.Pregunta;
/**
 * Clase PreguntaService 
 * servicio para las preguntas
 * 
 */
public class PreguntaService {
	/**
	 * Instacia de PreguntaDAO
	 * 
	 */
	private PreguntaDAO preguntaDAO;
	
	 /**
	  * Contructor clase PreguntaService
	  */
	public PreguntaService() {
		this.preguntaDAO = new PreguntaDAO();
	}
	/**
	 * Método guardarPregunta
	 * guarda una pregunta en la base de datos
	 * 
	 * @see PreguntaDAO -> save(Pregunta pregunta)
	 * @param Pregunta pregunta
	 * @return void
	 */
	public void guardarPregunta(Pregunta pregunta) {
		preguntaDAO.save(pregunta);  
	}

	/**
	 * Método listarPreguntasDeUnaEncuesta
     * Método que devuelve una lista de preguntas cuya encuesta se pasa como parámetro
     *
     * @param idEncuesta 
     * @return List<Pregunta>
     */
    public List<Pregunta> listarPreguntasDeUnaEncuesta(int idEncuesta) {
        List<Pregunta> todasLasPreguntas = preguntaDAO.getAll();

        return todasLasPreguntas.stream()
                .filter(pregunta -> pregunta.getEncuesta().getIdEncuesta() == idEncuesta)
                .collect(Collectors.toList());
    }
}