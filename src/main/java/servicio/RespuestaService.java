package servicio;

import dao.RespuestaDAO;
import modelo.Respuesta;

public class RespuestaService {

    private RespuestaDAO respuestaDAO;

    public RespuestaService() {
        this.respuestaDAO = new RespuestaDAO();
    }

    public void guardarRespuesta(Respuesta respuesta) {
        respuestaDAO.save(respuesta);
    }
}
