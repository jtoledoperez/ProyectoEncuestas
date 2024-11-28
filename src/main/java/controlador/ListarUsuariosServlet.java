package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import servicio.UsuarioService;

/**
 * Servlet implementation class ListarUsuariosServlet
 */
@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {

	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
	    super.init();
	    usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
	    request.setAttribute("usuarios", usuarios);
	    request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
	}
}
