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
import model.PromoAxB;
import model.Promocion;
import model.Tipo;
import persistence.TipoDAO;
import persistence.commons.DAOFactory;
import services.PromocionService;

@WebServlet("/admin-promociones/modificar.do")
public class ModificarPromocionServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -5003319901104367038L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Promocion promocion = null;
	
			try {
				promocion = DAOFactory.getPromocionDAO().obtenerPorId(id);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("promocion", promocion);
		
		if(promocion.esAbsoluta()) {
			req.setAttribute("porcentual", 0);
			req.setAttribute("absoluta", promocion.getDescuento());
			req.setAttribute("axb", 0);
		} else if(promocion.esPorcentual()) {
			req.setAttribute("porcentual", promocion.getDescuento());
			req.setAttribute("absoluta", 0);
			req.setAttribute("axb", 0);
		} else if (promocion.esPromoAxB()) {
			PromoAxB promo = (PromoAxB) promocion;
			req.setAttribute("porcentual", 0);
			req.setAttribute("absoluta", 0);
			req.setAttribute("axb",promo.getAtraccionGratis().getId());
		}
		
		req.setAttribute("atracciones", promocionService.buscarAtracciones());
		req.setAttribute("tipos", promocionService.buscarTipos());

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/modificar.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		String nombre = req.getParameter("nombre");
		String tipoPromocion = req.getParameter("tipoPromocion");
		
		int descuento = 0;
		if(tipoPromocion.equals("Absoluta")) {
			descuento = Integer.valueOf(req.getParameter("descuento"));
		} else if(tipoPromocion.equals("AxB")) {
			descuento = Integer.valueOf(req.getParameter("atraccionGratis"));
		} else if(tipoPromocion.equals("Porcentual")) {
			descuento = Integer.valueOf(req.getParameter("porcentaje"));
		}
		
		int tipoAtraccionId = Integer.valueOf(req.getParameter("tipoAtraccion"));
		String[] atracciones = req.getParameterValues("atracciones"); 
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");
		
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipoAtraccion = tipoDAO.buscarPorId(tipoAtraccionId);
		
		Promocion promocion = null;
		try {
			promocion = promocionService.modificar(id,tipoPromocion, nombre, atracciones, tipoAtraccion, descuento,imagen,descripcion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (promocion.esValido()) {
			resp.sendRedirect("/turismo/admin-promociones.do");
		
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promociones/modificar.jsp");
			dispatcher.forward(req, resp);
		}
		
	}
}
