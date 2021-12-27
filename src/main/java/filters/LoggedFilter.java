package filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import model.Usuario;

@WebFilter(urlPatterns = "*.do")
public class LoggedFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
		if (usuario != null) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("flash", "Por favor, ingresa al sistema");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ingresar.jsp");
			dispatcher.forward(request, response);
		}

	}

}
