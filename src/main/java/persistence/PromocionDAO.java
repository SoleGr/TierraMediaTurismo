package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Atraccion;
import model.Promocion;

public interface PromocionDAO {

    public abstract List<Promocion> obtenerTodos();

    public int actualizar(Promocion promocion);

    public Promocion toPromocion(Object objeto);

    List<Atraccion> obtenerAtraccionesDePromocion(int anInt) throws SQLException;

	public abstract int insertar(Promocion promocion);
	
	public Promocion obtenerPorId(int id) throws SQLException;
	
	public int eliminar(int id);

	public abstract int modificar(Promocion promo);
}
