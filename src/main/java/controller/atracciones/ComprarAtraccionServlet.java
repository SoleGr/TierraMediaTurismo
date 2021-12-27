package controller.atracciones;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Usuario;
import persistence.commons.DAOFactory;
import services.AtraccionService;

@WebServlet("/atracciones/comprar.do")
public class ComprarAtraccionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -7939597524495394303L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int atraccionId = Integer.valueOf(req.getParameter("id"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		try {
			atraccionService.comprarAtraccion(atraccionId, usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!usuario.getErrores().containsKey("comprar")) {
			req.setAttribute("ok", "¡Gracias por comprar!");
			
		} else {
			req.setAttribute("error", usuario.getErrores().get("comprar"));
			usuario.getErrores().remove("compra");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuarios/perfil.jsp");
		dispatcher.forward(req, resp);
	}

}
