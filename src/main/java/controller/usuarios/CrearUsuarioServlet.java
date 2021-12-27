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
import utils.Crypt;

@WebServlet("/admin-usuarios/crear.do")
public class CrearUsuarioServlet extends HttpServlet implements Servlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5505530272574025866L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		List<Tipo> tipos = tipoDAO.obtenerTodos();
		req.setAttribute("tipos", tipos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuarios/crear.jsp");
		dispatcher.forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		Integer monedas = Integer.parseInt(req.getParameter("monedas"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		int tipoId = Integer.valueOf(req.getParameter("tipo"));
		String imagenPerfil = req.getParameter("imagen");
		String hashContrasenia = Crypt.hash(req.getParameter("contrasenia"));
		Boolean activo = true;
		Boolean admin = Boolean.valueOf(req.getParameter("admin"));
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipo = tipoDAO.buscarPorId(tipoId);
		
		Usuario usuario = usuarioService.crear(nombre,tipo,monedas,tiempo,imagenPerfil,hashContrasenia,activo,admin);
		if (usuario.esValido()) {
			//resp.sendRedirect("/turismo/admin-usuarios/crear.do");
			resp.sendRedirect("/turismo/admin-usuarios.do");
		} else {
			req.setAttribute("usuarioCreado", usuario);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/usuarios/crear.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
