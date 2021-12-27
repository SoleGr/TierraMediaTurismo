package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Promocion implements Producto, Comparable<Promocion> {
	protected String nombre;
	protected List<Atraccion> atracciones;
	protected Tipo tipoAtraccion;
	protected String tipoPromocion;
	protected int id;
	protected String descripcion;
	protected String imagen;
	protected int descuento;
	protected Boolean activo;
	private Map<String, String> errores;
	
	public Promocion(int id, String nombre, Tipo tipoAtraccion, String tipoPromocion, int descuento,String descripcion,String imagen, Boolean activo,List<Atraccion> atracciones) {
		this.id = id;
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		this.tipoPromocion = tipoPromocion;
		this.descuento = descuento;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.activo = activo;
		this.atracciones = atracciones;
	}

	public int getId() {
		return this.id;
	}


	public void setTipo(Tipo tipoAtraccion) {
		this.tipoAtraccion = tipoAtraccion;
	}
	
	public Tipo getTipo() {
		return tipoAtraccion;
	}

	public String getTipoPromocion() {
		return tipoPromocion;
	}

	public void setTipoPromocion(String tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Atraccion> getAtraccionesTotales() {
		return atracciones;
	}


	public int compareTo(Promocion otra) {
		if (this.getPrecio().compareTo(otra.getPrecio()) == 0) {
			return -this.getTiempo().compareTo(otra.getTiempo());
		}
		return -this.getPrecio().compareTo(otra.getPrecio());
	}

	public Double getTiempo() {
		double total = 0;
		for (int i = 0; i < this.atracciones.size(); i++) {
			total += this.atracciones.get(i).getTiempo();
		}
		return total;
	}

	public Integer getPrecio() {
		int total = 0;

		for (int i = 0; i < this.atracciones.size(); i++) {
			total += this.atracciones.get(i).getPrecio();
		}
		return total;
	}

	public boolean tieneCupo() {
		boolean hay = true;
		for (Atraccion atraccion : atracciones) {
			hay &= atraccion.tieneCupo();
		}
		return hay;
	}
	
	public boolean tieneCupo(Promocion promocion) {
		boolean hay = true;
		for (Atraccion atraccion : atracciones) {
			hay &= atraccion.tieneCupo();
		}
		return hay;
	}

	public void actualizarCupo() {
		for (Atraccion atraccion : atracciones) {
			atraccion.actualizarCupo();
		}
	}

	public String getTipoPromo() {
    	if(this.esPromoAxB()) {
    		return "AxB";
    	}else if(this.esAbsoluta()) {
    		return "Absoluta";
    	}else if(this.esPorcentual()) {
    		return "Porcentual";
    	}return "No es PROMO";
    }
	
	public boolean esPromocion() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getListado() {
		String listado = "";
		for (Atraccion atraccion : atracciones) {
			listado += atraccion.getNombre() + "/";
		}
		return listado;
	}
	
	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}
	
	public void validar() {
		errores = new HashMap<String, String>();
		if (nombre.equals("") || nombre == null) {
			errores.put("nombre", "Debés ingresar un nombre");
		}
		
		if (tipoAtraccion == null) {
			errores.put("tipoAtraccion", "Debés elegir un tipo de atracción");
		}
		
		if (tipoPromocion.equals("") || tipoPromocion == null) {
			errores.put("tipoPromocion", "Debés elegir un tipo de promoción");
		}
		
		if (nombre.equals("") || nombre == null) {
			errores.put("descripcion", "Debés ingresar una descripcion");
		}
		
		if (atracciones == null || atracciones.size()<2) {
			errores.put("atracciones", "Debés seleccionar 2 o más atracciones");
		}
	}
	
	public Boolean estaActivo() {
		return activo;
	}
	
	
}
