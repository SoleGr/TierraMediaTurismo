package controller.usuarios;

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
import services.UsuarioService;

@WebServlet("/perfil.do")
public class PerfilServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -3639704310213287676L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuarios/perfil.jsp");
		dispatcher.forward(req, resp);
	}
}
