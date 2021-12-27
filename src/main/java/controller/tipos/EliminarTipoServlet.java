package controller.tipos;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TipoService;

@WebServlet("/admin-tipos/eliminar.do")
public class EliminarTipoServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -8477081821346493097L;
	private TipoService tipoService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tipoService = new TipoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		tipoService.eliminar(id);

		//.do
		resp.sendRedirect("/turismo/admin-tipos");
	}

}
