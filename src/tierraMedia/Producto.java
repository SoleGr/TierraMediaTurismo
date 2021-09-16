package tierraMedia;

import tierraMedia.atracciones.Atraccion;
import tierraMedia.atracciones.TipoAtraccion;

import java.util.List;

public interface Producto{

    Integer getCosto();

    Double getTiempo();

    String getNombre();

    TipoAtraccion getTipo();

    boolean tieneCupo();

    void actualizarCupo();

    List<Atraccion> getAtraccionesTotales();



}
