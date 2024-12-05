package servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

import dao.PreguntaDAO;
import dao.RespuestaDAO;
import hibernate.HibernateManager;
import modelo.Pregunta;
import modelo.Respuesta;
/**
 * Clace RespuestaService
 * servicio para las respuestas
 * 
 */
public class RespuestaService {
	/**
	 * Instacia de RespuestaDAO
	 * 
	 */
    private RespuestaDAO respuestaDAO;
    
    /**
	 * Instacia de PreguntaDAO
	 * 
	 */
    private PreguntaDAO preguntaDAO;

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
    
    /**
     * Método listarRespuestasDeUnaPregunta
     * Método que devuelve una lista de respuestas cuya pregunta se pasa como parámetro
     * 
     * @param idPregunta 
     * @return List<Respuesta> 
     */
    public List<Respuesta> listarRespuestasDeUnaPregunta(int idPregunta) {
        try (Session session = HibernateManager.getSessionFactory().openSession()) {
            String hql = "FROM Respuesta WHERE pregunta.idPregunta = :idPregunta";
            return session.createQuery(hql, Respuesta.class)
                          .setParameter("idPregunta", idPregunta)
                          .list();
        } catch (Exception e) {
            System.err.println("Error obteniendo respuestas de la pregunta con ID " + idPregunta + ": " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
