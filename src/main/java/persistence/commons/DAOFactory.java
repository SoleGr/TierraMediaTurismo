package persistence.commons;

import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.TipoDAO;
import persistence.UsuarioDAO;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.ItinerarioDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.TipoDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}

	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}

	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}

	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}

	public static TipoDAO getTipoDAO() {
		return new TipoDAOImpl();
	}
}
