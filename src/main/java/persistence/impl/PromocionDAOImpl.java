package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.PromoPorcentual;
import model.Promocion;
import model.Tipo;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.TipoDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public ArrayList<Promocion> obtenerTodos() {

		try {
			String query = "SELECT * FROM promocion";
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			ArrayList<Promocion> promociones = new ArrayList<>();

			while (rs.next()) {
				promociones.add(toPromocion(rs));
			}

			return promociones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int actualizar(Promocion promocion) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		for (Atraccion atraccion : promocion.getAtraccionesTotales()) {
			atraccionDAO.actualizar(atraccion);
		}
		return 0;
	}

	@Override
	public Promocion toPromocion(Object objeto) {
		ResultSet resultados = (ResultSet) objeto;
		Promocion promocion = null;
		int idPromo;

		try {
			// int id, String nombre, String tipoAtraccion, String tipoPromocion, int
			// descuento,
			// String descripcion,String imagen, Boolean activo,List<Atraccion> atracciones
			idPromo = resultados.getInt(1);
			String nombre = resultados.getString(2);
			int idTipoAtraccion = resultados.getInt(3);
			int idTipoPromo = resultados.getInt(4);
			String descuento = resultados.getString(5);
			String descripcion = resultados.getString(6);
			String imagen = resultados.getString(7);
			Boolean activo = Boolean.valueOf(resultados.getString(8));

			TipoDAO tipoDAO = DAOFactory.getTipoDAO();
			Tipo tipo_atraccion = tipoDAO.buscarPorId(idTipoAtraccion);

			String tipo_promocion = obtenerTipoNombre(idTipoPromo);

			List<Atraccion> atracciones = this.obtenerAtraccionesDePromocion(idPromo);

			switch (tipo_promocion) {
			case "AXB":
				AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
				Atraccion atraccionGratis = atraccionDAO.obtenerPorNombre(descuento);
				promocion = new PromoAxB(idPromo, nombre, tipo_atraccion, "AxB", atraccionGratis.getPrecio(),
						descripcion, imagen, activo, atracciones, atraccionGratis);
				break;
			case "ABSOLUTA":
				promocion = new PromoAbsoluta(idPromo, nombre, tipo_atraccion, descripcion, imagen, activo, "ABSOLUTA",
						atracciones, Integer.parseInt(descuento));
				promocion.setDescripcion(descripcion);
				promocion.setImagen(imagen);

				break;
			case "PORCENTUAL":
				promocion = new PromoPorcentual(idPromo, nombre, tipo_atraccion, "PORCENTUAL", descripcion, imagen,
						activo, atracciones, Integer.parseInt(descuento));
				promocion.setDescripcion(descripcion);
				promocion.setImagen(imagen);

				break;
			default:
				throw new RuntimeException("Tipo de promoción inexistente.");
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return promocion;
	}

	public List<Atraccion> obtenerAtraccionesDePromocion(int idPromocion) throws SQLException {

		String sql = "SELECT * FROM atraccion_x_promocion WHERE promocion_id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement2 = conn.prepareStatement(sql);
		statement2.setInt(1, idPromocion);
		ResultSet resultados = statement2.executeQuery();

		List<Atraccion> atracciones = new ArrayList<>();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

		while (resultados.next()) {
			Atraccion atraccion = atraccionDAO.obtenerPorId(resultados.getInt(2));
			atracciones.add(atraccion);
		}

		return atracciones;
	}

	public String obtenerTipoNombre(int idTipo) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql2 = "SELECT nombre FROM tipo_promocion WHERE id = ?";
		PreparedStatement statement2 = conn.prepareStatement(sql2);
		statement2.setInt(1, idTipo);

		ResultSet resultadoId = statement2.executeQuery();

		return resultadoId.getString(1).toUpperCase();
	}

	@Override
	public int insertar(Promocion promocion) {
		try {
			String sql = "INSERT INTO promocion (nombre, tipo_atraccion_id, tipo_promocion, descuento, descripcion, imagen, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setInt(2, promocion.getTipo().getId());

			String descuento = "";
			if (promocion.esAbsoluta()) {
				statement.setInt(3, 2);
				PromoAbsoluta absoluta = (PromoAbsoluta) promocion;
				descuento = String.valueOf(absoluta.getDescuento());
			} else if (promocion.esPromoAxB()) {
				statement.setInt(3, 1);
				PromoAxB axb = (PromoAxB) promocion;
				descuento = String.valueOf(axb.getAtraccionGratis().getNombre());
			} else if (promocion.esPorcentual()) {
				statement.setInt(3, 3);
				PromoPorcentual porcentual = (PromoPorcentual) promocion;
				descuento = String.valueOf(porcentual.getPorcentaje());
			}

			statement.setString(4, descuento);
			statement.setString(5, promocion.getDescripcion());
			statement.setString(6, promocion.getImagen());
			statement.setString(7, "true");
			int rows = statement.executeUpdate();

			int promocionId = this.obtenerId(promocion.getNombre());
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			for (Atraccion atraccion : promocion.getAtracciones()) {
				this.insertarAtraccionesDePromocion(promocionId, atraccion.getId());
			}

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	private int obtenerId(String nombre) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql2 = "SELECT id FROM promocion WHERE nombre = ?";
		PreparedStatement statement2 = conn.prepareStatement(sql2);
		statement2.setString(1, nombre);

		ResultSet resultadoId = statement2.executeQuery();

		return resultadoId.getInt(1);
	}

	private int insertarAtraccionesDePromocion(int promocionId, int atraccionId) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql = "INSERT INTO atraccion_x_promocion (promocion_id,atraccion_id) values (?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, promocionId);
		statement.setInt(2, atraccionId);
		int rows = statement.executeUpdate();

		return rows;
	}

	public Promocion obtenerPorId(int id) throws SQLException {

			String query = "SELECT * FROM promocion WHERE id = ?";
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			return toPromocion(rs);
	}

	public int eliminar(int id) {
		try {
			String sql = "UPDATE promocion SET activo = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, "false");
			statement.setDouble(2, id);
			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int modificar(Promocion promocion) {
		try {
			String sql = "UPDATE atraccion SET nombre = ?, tipo_atraccion_id = ?, tipo_promocion = ?, descuento = ?, descripcion = ?, imagen = ?  WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setInt(2, promocion.getTipo().getId());
			statement.setString(3, promocion.getTipoPromocion());
			String descuento = "";
			if (promocion.esAbsoluta()) {
				statement.setInt(4, 2);
				PromoAbsoluta absoluta = (PromoAbsoluta) promocion;
				descuento = String.valueOf(absoluta.getDescuento());
			} else if (promocion.esPromoAxB()) {
				statement.setInt(4, 1);
				PromoAxB axb = (PromoAxB) promocion;
				descuento = String.valueOf(axb.getAtraccionGratis().getNombre());
			} else if (promocion.esPorcentual()) {
				statement.setInt(4, 3);
				PromoPorcentual porcentual = (PromoPorcentual) promocion;
				descuento = String.valueOf(porcentual.getPorcentaje());
			}
			statement.setString(5, descuento);
			statement.setString(6, promocion.getDescripcion());
			statement.setString(7, promocion.getImagen());
			statement.setInt(8, promocion.getId());

			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	

}
