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
import model.Tipo;
import persistence.TipoDAO;
import persistence.commons.DAOFactory;
import services.AtraccionService;

@WebServlet("/admin-atracciones/modificar.do")
public class ModificarAtraccionServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -1245639632973569460L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Atraccion atraccion = DAOFactory.getAtraccionDAO().obtenerPorId(id);
		req.setAttribute("atraccion", atraccion);
		
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		List<Tipo> tipos = tipoDAO.obtenerTodos();
		req.setAttribute("tipos", tipos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/modificar.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		Integer precio = Integer.parseInt(req.getParameter("precio"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		int cupo = Integer.valueOf(req.getParameter("cupo"));
		int tipoId = Integer.valueOf(req.getParameter("tipo"));
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");
		
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipo = tipoDAO.buscarPorId(tipoId);
		
		Atraccion atraccion = atraccionService.modificar(id,nombre,tipo,precio,tiempo,cupo,imagen,descripcion);
		
		if (atraccion.esValido()) {
			resp.sendRedirect("/turismo/admin-atracciones.do");
		
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/atracciones/modificar.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
