package controller.atracciones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;
import services.AtraccionService;

@WebServlet("/atracciones")
public class OfrecerAtraccionesServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 636287873703578448L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.obtenerTodos();
		req.setAttribute("atracciones", atracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/atracciones.jsp");
		dispatcher.forward(req, resp);

	}
}
