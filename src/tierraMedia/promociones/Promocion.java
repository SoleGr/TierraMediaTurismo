package tierraMedia.promociones;

import tierraMedia.atracciones.Atraccion;
import tierraMedia.Producto;
import tierraMedia.atracciones.TipoAtraccion;

import java.util.List;

public abstract class Promocion implements Producto, Comparable<Promocion> {
    protected String nombre;
    protected List<Atraccion> atracciones;
    protected TipoAtraccion tipoAtraccion;

    public Promocion(String nombre, TipoAtraccion tipoAtraccion, List<Atraccion> atracciones) {
        this.nombre = nombre;
        this.tipoAtraccion = tipoAtraccion;
        this.atracciones = atracciones;
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

    public TipoAtraccion getTipo() {
        return tipoAtraccion;
    }

    public int compareTo(Promocion otra) {
        if (this.getCosto().compareTo(otra.getCosto()) == 0){
            return -this.getTiempo().compareTo(otra.getTiempo());
        }
        return -this.getCosto().compareTo(otra.getCosto());
    }

    public Double getTiempo() {
        double total = 0;
        for (int i = 0; i < this.atracciones.size(); i++) {
            total += this.atracciones.get(i).getTiempo();
        }
        return total;
    }

    public Integer getCosto() {
        int total = 0;

        for (int i = 0; i < this.atracciones.size(); i++) {
            total += this.atracciones.get(i).getCosto();
        }
        return total;
    }

    public boolean tieneCupo() {
        boolean hay = true;
        for(Atraccion atraccion:atracciones){
            hay &= atraccion.tieneCupo();
        }
        return hay;
    }

    public void actualizarCupo() {
        for(Atraccion atraccion:atracciones){
            atraccion.actualizarCupo();
        }
    }


}
