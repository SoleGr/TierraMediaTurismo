package controller.sugerencias;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import model.Usuario;
import services.SugerenciasService;

@WebServlet("/productos.do")
public class SugerenciasServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -2391780229411329691L;
	private SugerenciasService sugerenciasService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.sugerenciasService = new SugerenciasService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		List<Producto> productos = sugerenciasService.generarSugerencia(usuario.getId());
		req.setAttribute("productos", productos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/sugerencias/sugerencias.jsp");
		dispatcher.forward(req, resp);

	}
}
