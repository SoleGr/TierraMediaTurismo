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
import model.Usuario;
import services.UsuarioService;

@WebServlet("/admin-usuarios.do")
public class ListarUsuariosServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 9064895174292144334L;
	
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Usuario> usuarios = usuarioService.listarActivos();
		req.setAttribute("usuarios", usuarios);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuarios/index.jsp");
		dispatcher.forward(req, resp);

	}

}
