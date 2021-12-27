package controller.promociones;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.AtraccionService;
import services.PromocionService;

@WebServlet("/promociones/comprar")
public class ComprarPromocionServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 2629516330953260537L;
	private PromocionService promocionService;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int atraccionId = Integer.valueOf(req.getParameter("id"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		try {
			promocionService.comprarPromocion(atraccionId, usuario);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if (!usuario.getErrores().containsKey("comprar")) {
			req.setAttribute("ok", "¡Gracias por comprar!");
			
		} else {
			req.setAttribute("error", usuario.getErrores().get("comprar"));
			usuario.getErrores().remove("compra");
		}

		// resp.sendRedirect("/turismo/perfil.do");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuarios/perfil.jsp");
		dispatcher.forward(req, resp);
	}
	
}
