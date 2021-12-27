package services;

import java.util.ArrayList;
import java.util.List;

import model.Tipo;
import model.Usuario;
import persistence.ItinerarioDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;
import utils.Crypt;

public class UsuarioService {
	
	public List<Usuario> list() {
		return DAOFactory.getUsuarioDAO().obtenerTodos();
	}
	
	public List<Usuario> listarActivos(){
		List<Usuario> todos = DAOFactory.getUsuarioDAO().obtenerTodos();
		List<Usuario> activos = new ArrayList<Usuario>();
		for (Usuario usuario : todos) {
			if(usuario.estaActivo()) {
				activos.add(usuario);
			}
		}
		return activos;
	}
	
	public Usuario crear(String nombre, Tipo preferencia, int monedas, double tiempo, String imagenPerfil, String hashContrasenia,Boolean activo,Boolean admin) {
		//int id, String nombre, String preferencia, int monedas, double tiempo, String imagenPerfil, String hashContrasenia,Boolean activo,Boolean admin

		Usuario usuario = new Usuario(-1,nombre,preferencia, monedas, tiempo,imagenPerfil, hashContrasenia, activo, admin);

		if (usuario.esValido()) {
			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			usuarioDAO.insertar(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return usuario;
	}
	
	public void eliminar(int id) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.eliminar(id);
	}

	public Usuario modificar(int id,String nombre,Tipo tipo,int monedas,Double tiempo,String imagenPerfil,String contrasenia,Boolean activo,Boolean admin) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(id);
		
		usuario.setNombre(nombre);
		usuario.setTipo(tipo);
		usuario.setMonedas(monedas);
		usuario.setTiempoDisponible(tiempo);
		if(!imagenPerfil.equals("")) {
			usuario.setImagenPerfil(imagenPerfil);
		}
		if(!contrasenia.equals("")) {
			usuario.setHashContrasenia(Crypt.hash(contrasenia));
		}
		
		usuarioDAO.modificar(usuario);
		return usuario;
	}

}
