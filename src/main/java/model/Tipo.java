package model;

import java.util.HashMap;
import java.util.Map;

public class Tipo {
	private String nombre;
	private int id;
	private String imagen;
	private Boolean activo;
	private String descripcion;
	
	private Map<String, String> errores;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Tipo(int id, String nombre, String imagen, Boolean activo, String descripcion) {
		this.nombre = nombre;
		this.id = id;
		this.imagen = imagen;
		this.activo = activo;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}
	
	public void validar() {
		errores = new HashMap<String, String>();
		if (nombre == null) {
			errores.put("nombre", "Debés ingresar un nombre");
		}
		
	}
	
	public Map<String, String> getErrores() {
		return errores;
	}

	public void setErrores(Map<String, String> errores) {
		this.errores = errores;
	}
	
	public Boolean estaActivo() {
		return activo;
	}
	
}
