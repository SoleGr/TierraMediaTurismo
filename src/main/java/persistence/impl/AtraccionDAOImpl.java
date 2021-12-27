package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Atraccion;
import model.Tipo;
import persistence.AtraccionDAO;
import persistence.TipoDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class AtraccionDAOImpl implements AtraccionDAO {

	public ArrayList<Atraccion> obtenerTodos() {

		try {
			String query = "SELECT * FROM atraccion";
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			ArrayList<Atraccion> atracciones = new ArrayList<>();
			while (rs.next()) {
				atracciones.add(toAtraccion(rs));
			}

			return atracciones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int actualizar(Atraccion atraccion) {
		try {
			String sql = "UPDATE atraccion SET cupo = ? WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupoDisponible());
			statement.setString(2, atraccion.getNombre());

			return statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion obtenerPorNombre(String atraccionNombre) {
		try {
			String sql = "SELECT * FROM atraccion WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccionNombre);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atraccion obtenerPorId(int id) {
		try {
			String sql = "SELECT * FROM atraccion WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atraccion toAtraccion(Object objeto) throws SQLException {
		ResultSet resultados = (ResultSet) objeto;

		int id = resultados.getInt(1);
		String nombre = resultados.getString(2);
		int costo = resultados.getInt(3);
		double tiempo = resultados.getDouble(4);
		int cupo = resultados.getInt(5);
		int idTipoAtraccion = resultados.getInt(6);
		String descripcion = resultados.getString(7);
		String imagen = resultados.getString(8);
		Boolean activo = Boolean.valueOf(resultados.getString(9));

		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipoAtraccion = tipoDAO.buscarPorId(idTipoAtraccion);
		// (int id, String nombre, int costo, double tiempo, int cupoDiario, Tipo tipo,
		// String descripcion, String imagen)
		Atraccion atraccion = new Atraccion(id, nombre, costo, tiempo, cupo, tipoAtraccion, descripcion, imagen,
				activo);
		atraccion.setDescripcion(descripcion);
		atraccion.setImagen(imagen);

		return atraccion;
	}

	public String obtenerTipoNombre(int idTipo) throws SQLException {

		String sql = "SELECT nombre FROM tipo_atraccion WHERE tipo_atraccion_id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, idTipo);
		ResultSet resultadoId = statement.executeQuery();

		String nombre = "";
		if (resultadoId.next()) {
			nombre = resultadoId.getString(1).toUpperCase();
		}

		return nombre;
	}

	@Override
	public int insertar(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO atraccion (nombre, precio, tiempo, cupo, tipo_atraccion_id, descripcion, imagen, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getPrecio());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupoDisponible());
			statement.setInt(5, atraccion.getTipo().getId());
			statement.setString(6, atraccion.getDescripcion());
			statement.setString(7, atraccion.getImagen());
			statement.setString(8, "true");
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int eliminar(int id) {
		try {
			String sql = "UPDATE atraccion SET activo = ? WHERE id = ?";
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

	public int modificar(Atraccion atraccion) {
		try {
			String sql = "UPDATE atraccion SET nombre = ?, precio = ?, tiempo = ?, cupo = ?, tipo_atraccion_id = ?, descripcion = ?, imagen = ?  WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getPrecio());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupoDisponible());
			statement.setInt(5, atraccion.getTipo().getId());
			statement.setString(6, atraccion.getDescripcion());
			statement.setString(7, atraccion.getImagen());
			statement.setInt(8, atraccion.getId());

			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int contarTodos() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM atraccion WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
