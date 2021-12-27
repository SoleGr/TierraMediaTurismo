package controller.promociones;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PromocionService;

@WebServlet("/admin-promociones/eliminar.do")
public class EliminarPromocionServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -8887234717231792496L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		promocionService.eliminar(id);

		resp.sendRedirect("/turismo/admin-promociones.do");
	}

	
}
