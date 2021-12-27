package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tipo;
import model.nullobjects.TipoNull;
import persistence.TipoDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class TipoDAOImpl implements TipoDAO {

	@Override
	public int actualizarTipo(Tipo tipoAtraccion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizar(Tipo tipoAtraccion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tipo toTipo(Object objeto) throws SQLException {
		ResultSet resultados = (ResultSet) objeto;

		int id = resultados.getInt(1);
		String nombre = resultados.getString(2);
		String imagen = resultados.getString(3);
		Boolean activo = Boolean.valueOf(resultados.getString(4));
		String descripcion = resultados.getString(5);

		Tipo tipo = new Tipo(id, nombre, imagen, activo, descripcion);

		return tipo;
	}

	@Override
	public List<Tipo> obtenerTodos() {
		try {
			String query = "SELECT * FROM tipo_atraccion";
			Connection con = ConnectionProvider.getConnection();

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			List<Tipo> tipos = new ArrayList<>();

			while (rs.next()) {
				tipos.add(toTipo(rs));
			}

			return tipos;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insertar(Tipo tipoAtraccion) {
		int rows = 0;
		String sql = "INSERT INTO tipo_atraccion (nombre, imagen, activo, descripcion) VALUES (?, ?, ?, ?)";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipoAtraccion.getNombre());
			statement.setString(2, tipoAtraccion.getImagen());
			statement.setString(3, "true");
			statement.setString(4, tipoAtraccion.getDescripcion());

			rows = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	public int eliminar(int id) {
		try {
			String sql = "UPDATE tipo_atraccion SET activo = ? WHERE tipo_atraccion_id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "false");
			statement.setInt(2, id);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Tipo buscarPorNombre(String nombreTipo) {

		try {
			String sql = "SELECT * FROM tipo_atraccion WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreTipo);
			ResultSet resultados = statement.executeQuery();

			Tipo tipo = TipoNull.build();

			if (resultados.next()) {
				tipo = toTipo(resultados);
			}

			return tipo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Tipo buscarPorId(int id) {
		try {
			String sql = "SELECT * FROM tipo_atraccion WHERE tipo_atraccion_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Tipo tipo = TipoNull.build();

			if (resultados.next()) {
				tipo = toTipo(resultados);
			}

			return tipo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int modificar(Tipo tipo) {
		try {
			String sql = "UPDATE tipo_atraccion SET nombre = ?, imagen = ?, descripcion = ? WHERE tipo_atraccion_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, tipo.getNombre());
			statement.setString(2, tipo.getImagen());
			statement.setString(3, tipo.getDescripcion());
			statement.setInt(4, tipo.getId());
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		
	}

}
