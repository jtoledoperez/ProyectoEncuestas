package servicio;

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
	 * Método obtenerPregunta
	 * recupera una pregunta de la base de datos cuyo id se pasa como parámetro
	 * 
	 * @see PreguntaDAO -> getById(int id)
	 * @param int id
	 * @return Pregunta
	 */
	public Pregunta obtenerPregunta(int id) {
		return preguntaDAO.getById(id); 
	}


}
