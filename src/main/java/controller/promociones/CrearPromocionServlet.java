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
import model.Atraccion;
import model.Promocion;
import model.Tipo;
import persistence.AtraccionDAO;
import persistence.TipoDAO;
import persistence.commons.DAOFactory;
import services.PromocionService;

@WebServlet("/admin-promociones/crear.do")
public class CrearPromocionServlet extends HttpServlet implements Servlet{
	
	private static final long serialVersionUID = 1700264657831357434L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		List<Tipo> tipos = tipoDAO.obtenerTodos();
		req.setAttribute("tipos", tipos);
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.obtenerTodos();
		req.setAttribute("atracciones", atracciones);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/crear.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String tipoPromocion = req.getParameter("tipoPromocion");
		int tipoAtraccionId = Integer.valueOf(req.getParameter("tipoAtraccion"));
		String[] atracciones = req.getParameterValues("atracciones"); 
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");
		Boolean activo = true;
		
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipoAtraccion = tipoDAO.buscarPorId(tipoAtraccionId);
		
		int descuento = 0;
		if(tipoPromocion.equals("Absoluta")) {
			descuento = Integer.valueOf(req.getParameter("descuento"));
		} else if(tipoPromocion.equals("AxB")) {
			descuento = Integer.valueOf(req.getParameter("atraccionGratis"));
		} else if(tipoPromocion.equals("Porcentual")) {
			descuento = Integer.valueOf(req.getParameter("porcentaje"));
		}
		
		
		Promocion promocion = promocionService.crear(tipoPromocion, nombre, atracciones, tipoAtraccion, descuento,imagen,descripcion,activo);
		
		if (promocion.esValido()) {
			resp.sendRedirect("/turismo/admin-promociones.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promociones/crear.jsp");
			dispatcher.forward(req, resp);
		}
		
	}


}
