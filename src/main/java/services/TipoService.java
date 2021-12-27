package services;

import java.util.ArrayList;
import java.util.List;

import model.Tipo;
import model.Usuario;
import persistence.TipoDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class TipoService {
	public List<Tipo> list() {
		List<Tipo> tipos = DAOFactory.getTipoDAO().obtenerTodos();
		List<Tipo> activos = new ArrayList<Tipo>();
		for (Tipo tipo : tipos) {
			if(tipo.estaActivo()) {
				activos.add(tipo);
			}
		}
		return activos;
	}

	public Tipo crear(String nombre, String imagen, String descripcion) {
		Tipo tipo = new Tipo(-1, nombre, imagen, true, descripcion);
		
		if(tipo.esValido()) {
			TipoDAO tipoDAO = DAOFactory.getTipoDAO();
			tipoDAO.insertar(tipo);
		}
		return tipo;
	}

	public Tipo modificar(int id,String nombre, String imagen, String descripcion) {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipo = tipoDAO.buscarPorId(id);
		
		tipo.setNombre(nombre);
		tipo.setActivo(true);
		if(!imagen.equals("")) {
			tipo.setImagen(imagen);
		}
		tipo.setDescripcion(descripcion);
		
		tipoDAO.modificar(tipo);
		
		return tipo;
	}

	public void eliminar(Integer id) {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		tipoDAO.eliminar(id);
		
	}
}
