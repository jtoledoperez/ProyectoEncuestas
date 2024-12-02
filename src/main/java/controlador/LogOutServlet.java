package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogOutServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cogemos la sesion
        HttpSession session = request.getSession(false); 
        
        if (session != null) {
            // La cerramos
            session.invalidate();
        }

        // Vamos al inicio 
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
