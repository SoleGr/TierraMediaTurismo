package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistence.commons.DAOFactory;


public class GestorDeSugerencias {
    private static GestorDeSugerencias instancia;
    private List<Usuario> usuarios;
    private List<Atraccion> atracciones;
    private List<Promocion> promociones;
    private List<Tipo> tiposAtraccion;

    public static GestorDeSugerencias getInstancia() {
        if (instancia == null) {
            instancia = new GestorDeSugerencias();
        }
        return instancia;
    }

    private GestorDeSugerencias() {
        this.usuarios = new ArrayList<>();
        this.atracciones = new ArrayList<>();
        this.promociones = new ArrayList<>();
        this.tiposAtraccion = new ArrayList<>();
    }

    public void cargarUsuarios() {
            this.usuarios = DAOFactory.getUsuarioDAO().obtenerTodos();
    }

    public void cargarPromociones() {
            this.promociones = DAOFactory.getPromocionDAO().obtenerTodos();
    }

    public void cargarAtracciones() {
        this.atracciones = DAOFactory.getAtraccionDAO().obtenerTodos();
    }
    
    public void cargarTipos() {
        this.tiposAtraccion = DAOFactory.getTipoDAO().obtenerTodos();
    }
    
    public void cargarTodo() {
    	 this.tiposAtraccion = DAOFactory.getTipoDAO().obtenerTodos();
    	 this.atracciones = DAOFactory.getAtraccionDAO().obtenerTodos();
    	 this.promociones = DAOFactory.getPromocionDAO().obtenerTodos();
    }


    public List<Atraccion> getAtracciones() {
        return this.atracciones;
    }

    public void setAtracciones(List<Atraccion> atracciones) {
        this.atracciones = atracciones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    private List<Promocion> agregarPromocionesOrdenadas(String tipo) {
        List<Promocion> sugerencias = new ArrayList<>();
        List<Promocion> otroTipo = new ArrayList<>();

        //Separo las promociones del tipo preferido de los otros
        for (Promocion promociones : promociones) {
            if (promociones.getTipo().getNombre().equals(tipo)) {
                sugerencias.add(promociones);
            } else
                otroTipo.add(promociones);
        }

        //Ordeno por costo y si hay empate por tiempo
        Collections.sort(sugerencias);
        Collections.sort(otroTipo);

        sugerencias.addAll(otroTipo);

        return sugerencias;
    }

    private List<Atraccion> agregarAtraccionesOrdenadas(String tipo) {
        List<Atraccion> sugerencias = new ArrayList<>();
        List<Atraccion> otrasAtracciones = new ArrayList<>();

        for (Atraccion atraccione : atracciones) {
            if (atraccione.getTipo().getNombre().equals(tipo)) {
                sugerencias.add(atraccione);
            } else {
                otrasAtracciones.add(atraccione);
            }
        }

        Collections.sort(sugerencias);
        Collections.sort(otrasAtracciones);

        sugerencias.addAll(otrasAtracciones);

        return sugerencias;
    }

    public List<Producto> generarSugerenciasPara(Usuario usuario) {
        List<Producto> sugerencias = new ArrayList<>();
        sugerencias.addAll(agregarPromocionesOrdenadas(usuario.getTipo().getNombre()));
        sugerencias.addAll(agregarAtraccionesOrdenadas(usuario.getTipo().getNombre()));
        return sugerencias;
    }

}
