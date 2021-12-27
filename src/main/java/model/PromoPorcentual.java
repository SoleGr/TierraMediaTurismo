package model;

import java.util.List;

public class PromoPorcentual extends Promocion {
    private int porcentaje;

    public PromoPorcentual(int id, String nombre, Tipo tipoAtraccion,String tipoPromocion,String descripcion,
    		String imagen,Boolean activo, List<Atraccion> atracciones, int porcentaje) {
    	super(id, nombre, tipoAtraccion, tipoPromocion,0,descripcion,imagen,activo, atracciones);
        this.porcentaje = porcentaje;
        int costo = this.getPrecio();
        super.setDescuento(costo);
    }

    @Override
    public Integer getPrecio() {
        return (int) Math.ceil(super.getPrecio() * (1 - this.porcentaje/100.0));
    }

    @Override
    public boolean esAtraccion() {
        return false;
    }

	public int getPorcentaje() {
		return porcentaje;
	}

	@Override
    public boolean esPromoAxB() {
        return false;
    }

	@Override
	public boolean esPorcentual() {
		return true;
	}

	@Override
	public boolean esAbsoluta() {
		return false;
	}
}
