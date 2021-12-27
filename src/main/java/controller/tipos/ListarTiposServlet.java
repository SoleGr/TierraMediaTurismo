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
import services.TipoService;

@WebServlet("/admin-tipos.do")
public class ListarTiposServlet extends HttpServlet implements Servlet{
	
	private static final long serialVersionUID = -8326909032903919367L;

	private TipoService tipoService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tipoService = new TipoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Tipo> tipos = tipoService.list();
		req.setAttribute("tipos", tipos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/tipos/index.jsp");
		dispatcher.forward(req, resp);

	}
}
