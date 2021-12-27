package services;

import java.util.List;

import model.GestorDeSugerencias;
import model.Producto;
import model.Usuario;
import persistence.commons.DAOFactory;

public class SugerenciasService {
	
	public List<Producto> generarSugerencia(int usuarioId){
		Usuario usuario = DAOFactory.getUsuarioDAO().buscarPorId(usuarioId);
		
		GestorDeSugerencias gestor = GestorDeSugerencias.getInstancia();
		gestor.cargarTodo();
		List<Producto> sugerencia = gestor.generarSugerenciasPara(usuario);
		
		return sugerencia;
	}
	
	
}
