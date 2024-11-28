package servicio;

import dao.PreguntaDAO;
import modelo.Pregunta;

public class PreguntaService {

	private PreguntaDAO preguntaDAO;

	public PreguntaService() {

		this.preguntaDAO = new PreguntaDAO();
	}

	public void guardarPregunta(Pregunta pregunta) {
		preguntaDAO.save(pregunta);  
	}

	public Pregunta obtenerPregunta(int id) {
		return preguntaDAO.getById(id); 
	}


}
