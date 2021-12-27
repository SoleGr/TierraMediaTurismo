package model;

import java.util.List;

public interface Producto{

    Integer getPrecio();

    Double getTiempo();

    String getNombre();

    Tipo getTipo();

    boolean tieneCupo();

    void actualizarCupo();

    List<Atraccion> getAtraccionesTotales();

    boolean esPromocion();

    boolean esAtraccion();

    boolean esPromoAxB();
    
    boolean esPorcentual();
    
    boolean esAbsoluta();
    
    int getId();
}
