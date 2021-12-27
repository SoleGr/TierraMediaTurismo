package controller.promociones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import services.PromocionService;

@WebServlet("/promociones")
public class OfrecerPromocionesServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 439724935719328094L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> promociones = promocionDAO.obtenerTodos();
		req.setAttribute("promociones", promociones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/promociones.jsp");
		dispatcher.forward(req, resp);

	}
}
