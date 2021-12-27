package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Atraccion;
import model.Tipo;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AtraccionService {
	private AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

	public List<Atraccion> list() {
		return atraccionDAO.obtenerTodos();
	}
	
	public List<Atraccion> listarActivos(){
		List<Atraccion> todos = this.list();
		List<Atraccion> activos = new ArrayList<Atraccion>();
		for (Atraccion atraccion : todos) {
			if(atraccion.estaActivo()) {
				activos.add(atraccion);
			}
		}
		return activos;
	}

	public Atraccion crear(String nombre, Tipo tipo, Integer costo, Double duracion, Integer cupo, String imagen, String descripcion, Boolean activo) {
		Atraccion atraccion = new Atraccion(-1, nombre, costo, duracion, cupo, tipo, descripcion, imagen,activo);

		if (atraccion.esValido()) {
			atraccionDAO.insertar(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public Atraccion modificar(int id,String nombre, Tipo tipo, Integer costo, Double duracion, Integer cupo, String imagen, String descripcion) {
		Atraccion atraccion = atraccionDAO.obtenerPorId(id);
		
		atraccion.setNombre(nombre);
		atraccion.setTiempo(duracion);
		atraccion.setPrecio(costo);
		atraccion.setCupoDisponible(cupo);
		atraccion.setImagen(imagen);
		atraccion.setDescripcion(descripcion);

		if (atraccion.esValido()) {
			atraccionDAO.modificar(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public void eliminar(int id) {
		atraccionDAO.eliminar(id);
	}

	public Atraccion find(Integer id) {
		return atraccionDAO.obtenerPorId(id);
	}

	public void comprarAtraccion(int atraccionId,Usuario usuario) throws SQLException {
		Atraccion atraccion = atraccionDAO.obtenerPorId(atraccionId);
		 usuario.validarCompra(atraccion);
	}
	
	public void delete(Integer id) {
		Atraccion atraccion = new Atraccion(id, null, 0, 0, 0,null,null,null,null);
		atraccionDAO.delete(atraccion);
	}

}
