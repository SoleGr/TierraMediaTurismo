package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public interface UsuarioDAO {

    public abstract int actualizarItinerario(Usuario usuario);

    public int actualizar(Usuario usuario);

    Usuario toUsuario(Object objeto) throws SQLException;

    public List<Usuario> obtenerTodos();
    
    public Usuario buscarPorNombre(String nombre);
    
    public int insertar(Usuario usuario);
    
    public int eliminar(int id);

	public abstract Usuario buscarPorId(Integer id);
	
	public int modificar(Usuario usuario);
}
