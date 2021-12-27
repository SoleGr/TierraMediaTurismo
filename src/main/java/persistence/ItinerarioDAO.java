package persistence;

import model.Atraccion;
import model.Producto;
import model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItinerarioDAO {

    public int actualizarItinerario(Usuario usuario);

    public List<Atraccion> obtenerAtraccionesCompradas(int id) throws SQLException;

	public List<Producto> obtenerProductosComprados(int id) throws SQLException;
}
