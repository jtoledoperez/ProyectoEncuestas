package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Encuesta;

import java.util.List;
import servicio.EncuestasService;

/**
 * Servlet implementation class ListarEncuestasServlet
 */
@WebServlet("/listar-encuestas")
public class ListarEncuestasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EncuestasService encuestasService;

	@Override
	public void init() throws ServletException {
		encuestasService = new EncuestasService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		List<Encuesta> encuestas = encuestasService.listarTodasLasEncuestas();

		if (encuestas == null || encuestas.isEmpty()) {
			request.setAttribute("mensaje", "No se encontraron encuestas.");
		} else {
			request.setAttribute("encuestas", encuestas);
		}

		request.getRequestDispatcher("/listarEncuestas.jsp").forward(request, response);
	}
}