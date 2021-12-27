package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;


public class Atraccion implements Producto, Comparable<Atraccion> {

    private int id;
    private String nombre;
    private Tipo tipo;
    private Integer precio;
    private Double tiempo;
    private int cupoDisponible;
    private String descripcion;
    private String imagen;
    private Boolean activo;
    private Map<String, String> errores;

    
    public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
    
    public Atraccion(int id, String nombre, int costo, double tiempo, int cupoDiario, Tipo tipo, String descripcion, String imagen, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = costo;
        this.tiempo = tiempo;
        this.cupoDisponible = cupoDiario;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public boolean tieneCupo() {
        return cupoDisponible > 0;
    }

    public void actualizarCupo() {
        this.cupoDisponible--;
        AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
        atraccionDAO.actualizar(this);

    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getTiempo() {
        return tiempo;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public Integer getPrecio() {
        return this.precio;
    }

    public void setPrecio(Integer costo) {
        this.precio = costo;
    }

    public List<Atraccion> getAtraccionesTotales() {
        List<Atraccion> atraccion = new ArrayList<>();
        atraccion.add(this);
        return atraccion;
    }

    @Override
    public boolean esPromocion() {
        return false;
    }

    @Override
    public boolean esAtraccion() {
        return true;
    }

    @Override
    public boolean esPromoAxB() {
        return false;
    }

    @Override
    public int compareTo(Atraccion otra) {
        // ordenar por costo y si es el mismo ordena por tiempo
        if (this.getPrecio().compareTo(otra.getPrecio()) == 0) {
            return -this.getTiempo().compareTo(otra.getTiempo());
        }
        return -this.getPrecio().compareTo(otra.getPrecio());
    }

    @Override
    public boolean esPorcentual() {
        return false;
    }

    @Override
    public boolean esAbsoluta() {
        return false;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atraccion atraccion = (Atraccion) o;
        return id == atraccion.id && Objects.equals(nombre, atraccion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
    
    public boolean esValido() {
		validar();
		return errores.isEmpty();
	}
	
	public void validar() {
		errores = new HashMap<String, String>();

		if (precio <= 0) {
			errores.put("precio", "Debe ser positivo");
		}
		if (tiempo <= 0) {
			errores.put("tiempo", "Debe ser positivo");
		}
		if (cupoDisponible <= 0) {
			errores.put("cupo", "Debe ser positivo");
		}
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	public void setErrores(Map<String, String> errores) {
		this.errores = errores;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

	public void setCupoDisponible(int cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
	}

	public boolean estaActivo() {
		return activo;
	}
}
