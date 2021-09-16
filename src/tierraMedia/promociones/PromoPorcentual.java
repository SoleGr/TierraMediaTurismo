package tierraMedia.promociones;

import tierraMedia.atracciones.Atraccion;
import tierraMedia.atracciones.TipoAtraccion;

import java.util.List;

public class PromoPorcentual extends Promocion {
    private int porcentaje;

    public PromoPorcentual(String nombre, TipoAtraccion tipoAtraccion, List<Atraccion> atracciones, int porcentaje) {
        super(nombre, tipoAtraccion, atracciones);
        this.porcentaje = porcentaje;
    }

    @Override
    public Integer getCosto() {
        return (int) Math.ceil(super.getCosto() * (1 - this.porcentaje/100.0));
    }


}
