package controller.tipos;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tipo;
import model.Usuario;
import persistence.commons.DAOFactory;
import services.TipoService;

@WebServlet("/admin-tipos/modificar.do")
public class ModificarTipoServlet extends HttpServlet {

	private static final long serialVersionUID = 8378696872102799518L;
	private TipoService tipoService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tipoService = new TipoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Tipo tipo = DAOFactory.getTipoDAO().buscarPorId(id);
		
		req.setAttribute("modificable", tipo);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/tipos/modificar.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String imagen = req.getParameter("imagen");

		Tipo tipo = tipoService.modificar(id, nombre, imagen, descripcion);
		
		if (tipo.esValido()) {
			resp.sendRedirect("/turismo/admin-tipos.do");
		} else {
			req.setAttribute("tipo", tipo);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/tipos/modificar.jsp");
			dispatcher.forward(req, resp);
		}
		
	}

}
