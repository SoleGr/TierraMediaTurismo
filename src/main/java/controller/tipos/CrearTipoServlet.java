package controller.tipos;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tipo;
import model.Usuario;
import persistence.TipoDAO;
import persistence.commons.DAOFactory;
import services.TipoService;
import utils.Crypt;

@WebServlet("/admin-tipos/crear.do")
public class CrearTipoServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 2472236810007258386L;
	private TipoService tipoService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tipoService = new TipoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/tipos/crear.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String imagen = req.getParameter("imagen");
		String descripcion = req.getParameter("descripcion");
		
		Tipo tipo = tipoService.crear(nombre,imagen,descripcion);
		if (tipo.esValido()) {
			
			resp.sendRedirect("/turismo/admin-tipos/crear.do");
		} else {
			req.setAttribute("tipo", tipo);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/tipos/crear.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
