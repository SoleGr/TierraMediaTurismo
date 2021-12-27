package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;
import utils.Crypt;

public class Usuario {
	private int id;
	private String nombre;
	private int monedas;
	private Tipo tipo;
	private double tiempoDisponible;
	private List<Producto> itinerario;
	private List<Atraccion> atraccionesCompradas;
	private List<Producto> productosComprados;
	private String imagenPerfil;
	private String hashContrasenia;
	private Boolean admin;
	private Boolean activo;
	private int dineroGastado;
	private double tiempoGastado;

	private Map<String, String> errores;

	public Usuario(int id, String nombre, Tipo preferencia, int monedas, double tiempo, String imagenPerfil,
			String hashContrasenia, Boolean activo, Boolean admin) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = preferencia;
		this.monedas = monedas;
		this.tiempoDisponible = tiempo;
		this.itinerario = new ArrayList<>();
		this.atraccionesCompradas = new ArrayList<>();
		this.productosComprados = new ArrayList<>();
		this.imagenPerfil = imagenPerfil;
		this.hashContrasenia = hashContrasenia;
		this.admin = admin;
		this.activo = activo;
		this.errores = new HashMap<String, String>();
		
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	public void setErrores(Map<String, String> errores) {
		this.errores = errores;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public Boolean esAdmin() {
		return admin;
	}

	public boolean isNull() {
		return false;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getMonedas() {
		return monedas;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setAtraccionesCompradas(List<Atraccion> atraccionesCompradas) {
		this.atraccionesCompradas = atraccionesCompradas;
	}

	public String getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public String getHashContrasenia() {
		return hashContrasenia;
	}

	public void setHashContrasenia(String hashContrasenia) {
		this.hashContrasenia = hashContrasenia;
	}

	public void adquirirProducto(Producto sugerencia) throws SQLException {
		this.itinerario.add(sugerencia);
		this.atraccionesCompradas.addAll(sugerencia.getAtraccionesTotales());
		this.productosComprados.add(sugerencia);
		this.actualizarUsuario(sugerencia);
		this.tiempoGastado += sugerencia.getTiempo();
		this.dineroGastado += sugerencia.getPrecio();
		sugerencia.actualizarCupo();
		this.actualizarItinerario();
	}

	public void validarCompra(Producto sugerencia) throws SQLException {
		errores = new HashMap<String, String>();
		if (this.puedeComprar(sugerencia)) {
			this.adquirirProducto(sugerencia);
		} else  {
			this.errorComprar(sugerencia);
		}
	}
	
	public void errorComprar(Producto sugerencia) {
		if (!sugerencia.tieneCupo()) {
			errores.put("comprar", "La atracción no tiene cupo.");
		} else if (this.monedas < sugerencia.getPrecio()) {
			errores.put("comprar", "No tenés monedas suficientes para comprar esta atracción.");
		} else if (this.tiempoDisponible < sugerencia.getTiempo()) {
			errores.put("comprar", "No tenés el tiempo suficiente para realizar esta atracción.");
		} else if (!this.noSeVisito(sugerencia)) {
			errores.put("comprar", "Ya compraste esta atracción.");
		}
	}

	public boolean puedeComprar(Producto sugerencia) {
		return this.monedas >= sugerencia.getPrecio() && this.tiempoDisponible >= sugerencia.getTiempo()
				&& this.noSeVisito(sugerencia) && sugerencia.tieneCupo();
	}

	private void actualizarUsuario(Producto sugerencia) throws SQLException {
		this.monedas -= sugerencia.getPrecio();
		this.tiempoDisponible -= sugerencia.getTiempo();
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

		usuarioDAO.actualizar(this);
	}

	public List<Atraccion> getAtraccionesCompradas() {
		return this.atraccionesCompradas;
	}

	public boolean noSeVisito(Producto sugerencia) {
		if (sugerencia.esAtraccion()) {
			return !atraccionesCompradas.contains((Atraccion) sugerencia);
		}

		if (sugerencia.esPromocion()) {
			List<Atraccion> atraccionesPromo = ((Promocion) sugerencia).getAtracciones();
			boolean noContieneGratis = true;
			if (sugerencia.esPromoAxB()) {
				PromoAxB axb = (PromoAxB) sugerencia;
				noContieneGratis = !atraccionesCompradas.contains(axb.getAtraccionGratis());
			}
			return Collections.disjoint(atraccionesCompradas, atraccionesPromo) && noContieneGratis;
		}
		return false;
	}

	public void setItinerario(List<Producto> itinerario) {
		this.itinerario = itinerario;
	}

	public List<Producto> getItinerario() {
		return itinerario;
	}

	public void actualizarItinerario() {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.actualizarItinerario(this);
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
		Usuario other = (Usuario) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	public boolean checkPassword(String password) {
		return Crypt.match(password, this.hashContrasenia);
	}

	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	public void validar() {
		errores = new HashMap<String, String>();

		if (monedas <= 0) {
			errores.put("monedas", "La cantidad de monedas debe ser positiva");
		}
		if (tiempoDisponible <= 0) {
			errores.put("duracion", "El tiempo disponible debe ser positivo");
		}
	}

	public Boolean estaActivo() {
		return activo;
	}

	public Boolean sinCompras() {
		return this.atraccionesCompradas.size() == 0;
	}

	public double getTiempoGastado() {
		return this.tiempoGastado;
	}

	public int getDineroGastado() {
		return this.dineroGastado;
	}

	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public void setDineroGastado(int dineroGastado) {
		this.dineroGastado = dineroGastado;
	}

	public void setTiempoGastado(double tiempoGastado) {
		this.tiempoGastado = tiempoGastado;
	}

}
