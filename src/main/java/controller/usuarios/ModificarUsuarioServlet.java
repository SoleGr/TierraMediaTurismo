package controller.usuarios;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/admin-usuarios/modificar.do")
public class ModificarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = -1410527637321647037L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Usuario usuario = DAOFactory.getUsuarioDAO().buscarPorId(id);
		
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		List<Tipo> tipos = tipoDAO.obtenerTodos();
		req.setAttribute("tipos", tipos);
		
		req.setAttribute("modificable", usuario);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuarios/modificar.jsp");
		dispatcher.forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		Integer monedas = Integer.parseInt(req.getParameter("monedas"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		int tipoId = Integer.valueOf(req.getParameter("tipo"));
		String imagenPerfil = req.getParameter("imagen");
		String contrasenia = req.getParameter("contrasenia");
		Boolean activo = true;
		Boolean admin = Boolean.valueOf(req.getParameter("admin"));
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipo = tipoDAO.buscarPorId(tipoId);
		Usuario usuario = usuarioService.modificar(id,nombre,tipo,monedas,tiempo,imagenPerfil,contrasenia,activo,admin);
		
		if (usuario.esValido()) {
			//resp.sendRedirect("/turismo/admin-usuarios/crear.do");
			resp.sendRedirect("/turismo/admin-usuarios.do");
		} else {
			req.setAttribute("usuario", usuario);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/usuarios/modificar.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
