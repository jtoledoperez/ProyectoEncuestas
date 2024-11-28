package servicio;

import dao.RespuestaDAO;
import modelo.Respuesta;
/**
 * Clace RespuestaService
 * servicio para las respuestas
 * 
 * @author Paula Ruano
 */
public class RespuestaService {
	/**
	 * Instacia de RespuestaDAO
	 * 
	 */
    private RespuestaDAO respuestaDAO;

    /**
	 * Contructor clase RespuestaService
	 */
    public RespuestaService() {
        this.respuestaDAO = new RespuestaDAO();
    }

    /**
	 * Método guardarRespuesta
	 * guarda una respuesta en la base de datos
	 * 
	 * @see RespuestasDAO -> save(Respuesta respuesta)
	 * @param Respuesta respuesta
	 * @return void
	 */
    public void guardarRespuesta(Respuesta respuesta) {
        respuestaDAO.save(respuesta);
    }
}
