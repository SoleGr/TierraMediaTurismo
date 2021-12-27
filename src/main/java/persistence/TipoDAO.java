package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Tipo;

public interface TipoDAO {
	
	 public abstract int actualizarTipo(Tipo tipoAtraccion);

	 public int actualizar(Tipo tipoAtraccion);

	 Tipo toTipo(Object objeto) throws SQLException;

	 public List<Tipo> obtenerTodos();
	 
	 public Tipo buscarPorNombre(String nombreTipo);
	 
	 public Tipo buscarPorId(int id);

	public abstract int insertar(Tipo tipo);

	public abstract int modificar(Tipo tipo);

	public abstract int eliminar(int id);
}
