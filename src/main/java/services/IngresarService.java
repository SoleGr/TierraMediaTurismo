package services;

import model.Usuario;
import model.nullobjects.UsuarioNull;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class IngresarService {

	public Usuario login(String nombreUsuario, String contrasenia) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
    	Usuario usuario = usuarioDAO.buscarPorNombre(nombreUsuario);
    	
    	if (usuario.isNull() || !usuario.checkPassword(contrasenia)) {
    		usuario = UsuarioNull.build();
    		}
    	return usuario;
	}
}
