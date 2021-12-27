package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/salir")
public class SalirServlet extends HttpServlet {

	private static final long serialVersionUID = 848769457200705651L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getSession().removeAttribute("usuario");
		req.setAttribute("flash", "¡Hasta pronto!");
		
		RequestDispatcher dispatcher = getServletContext()
  		      .getRequestDispatcher("/index.jsp");
  		    dispatcher.forward(req, resp); 	
    }
}
