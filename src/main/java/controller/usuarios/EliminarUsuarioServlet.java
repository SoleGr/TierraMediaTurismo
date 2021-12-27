package controller.usuarios;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UsuarioService;

@WebServlet("/admin-usuarios/eliminar.do")
public class EliminarUsuarioServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -3950471947962978031L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		usuarioService.eliminar(id);

		resp.sendRedirect("/turismo/admin-usuarios.do");
	}
}
