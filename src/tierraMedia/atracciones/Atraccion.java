package tierraMedia.atracciones;

import tierraMedia.Producto;

import java.util.ArrayList;
import java.util.List;

public class Atraccion implements Producto, Comparable<Atraccion> {

    private String nombre;
    private TipoAtraccion tipo;
    private Integer costo;
    private Double tiempo;
    private int cupoDisponible;

    public Atraccion(String nombre, int costo, double tiempo, int cupoDiario, String tipo) {
        this.nombre = nombre;
        this.tipo = TipoAtraccion.valueOf(tipo.toUpperCase());
        this.costo = costo;
        this.tiempo = tiempo;
        this.cupoDisponible = cupoDiario;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoAtraccion getTipo() {
        return this.tipo;
    }

    public boolean tieneCupo() {
        return cupoDisponible > 0;
    }

    public void actualizarCupo() {
        this.cupoDisponible--;
    }

    public Double getTiempo() {
        return tiempo;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public Integer getCosto() {
        return this.costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public List<Atraccion> getAtraccionesTotales(){
        List<Atraccion> atraccion = new ArrayList<>();
        atraccion.add(this);
        return atraccion;
    }

    @Override
    public int compareTo(Atraccion otra) {
        //ordenar por costo y si es el mismo ordena por tiempo
        if (this.getCosto().compareTo(otra.getCosto()) == 0) {
            return -this.getTiempo().compareTo(otra.getTiempo());
        }
        return -this.getCosto().compareTo(otra.getCosto());
    }
}
