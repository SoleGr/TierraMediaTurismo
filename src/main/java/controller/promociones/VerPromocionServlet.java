package controller.promociones;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;
import persistence.commons.DAOFactory;
import services.PromocionService;

@WebServlet("/productos/promocion")
public class VerPromocionServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -2913407898627160173L;
	private PromocionService promocionService;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Promocion promocion;
		try {
			promocion = DAOFactory.getPromocionDAO().obtenerPorId(id);
			req.setAttribute("promocion", promocion);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/verPromocion.jsp");
		dispatcher.forward(req, resp);
	}
	
	
}
