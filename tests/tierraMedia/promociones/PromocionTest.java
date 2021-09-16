package tierraMedia.promociones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tierraMedia.atracciones.Atraccion;
import tierraMedia.atracciones.TipoAtraccion;

import java.util.ArrayList;
import java.util.List;

public class PromocionTest {
    List<Atraccion> atracciones;
    PromoPorcentual porcentual;
    PromoAbsoluta absoluta;
    PromoAxB axb;
    Atraccion atraccionGratis;

    @Before
    public void setUp() {
        atracciones = new ArrayList<>();
        atracciones.add(new Atraccion("atraccion1", 40, 1, 1, "AVENTURA"));
        atracciones.add(new Atraccion("atraccion2", 60, 1, 1, "AVENTURA"));
        atraccionGratis = new Atraccion("atraccionGratis", 10, 1, 1, "AVENTURA");

        porcentual = new PromoPorcentual("PROMO PORCENTUAL", TipoAtraccion.valueOf("AVENTURA"), atracciones, 50);
        absoluta = new PromoAbsoluta("PROMO ABSOLUTA", TipoAtraccion.valueOf("AVENTURA"), atracciones, 10);
        axb = new PromoAxB("PROMO AXB", TipoAtraccion.valueOf("AVENTURA"), atracciones, atraccionGratis);
    }

    @Test
    public void costoPromoPorcentualTest() {
        Assert.assertEquals(50, (int) porcentual.getCosto());
    }

    @Test
    public void costoPromoAbsolutaTest() {
        Assert.assertEquals(90, (int) absoluta.getCosto());
    }

    @Test
    public void costoPromoAxBTest() {
        Assert.assertEquals(100, (int) axb.getCosto());
    }

    @Test
    public void tiempoPromoPorcentualTest() {
        Assert.assertEquals(2, porcentual.getTiempo(), 0);
    }

    @Test
    public void tiempoPromoAbsolutaTest() {
        Assert.assertEquals(2, absoluta.getTiempo(), 0);
    }

    @Test
    public void tiempoPromoAxBTest() {
        Assert.assertEquals(3, axb.getTiempo(), 0);
    }

    @Test
    public void tieneCupoPorcentualTest() {
        Assert.assertEquals(true, porcentual.tieneCupo());
        porcentual.actualizarCupo();
        Assert.assertEquals(false, porcentual.tieneCupo());

    }

    @Test
    public void tieneCupoAbsolutaTest() {
        Assert.assertEquals(true, absoluta.tieneCupo());
        absoluta.actualizarCupo();
        Assert.assertEquals(false, absoluta.tieneCupo());
    }

    @Test
    public void tieneCupoAxBTest() {
        Assert.assertEquals(true, axb.tieneCupo());
        axb.actualizarCupo();
        Assert.assertEquals(false, axb.tieneCupo());
    }

    @Test
    public void verCupoTest(){
        Assert.assertEquals(3,axb.getAtraccionesTotales().size());

        for(Atraccion atr : axb.getAtraccionesTotales()){
            Assert.assertEquals(1,atr.getCupoDisponible());
        }
    }

    @Test
    public void actualizarCupoTest(){
        axb.actualizarCupo();
        for(Atraccion atr : axb.getAtraccionesTotales()){
            Assert.assertEquals(0,atr.getCupoDisponible());
        }
    }


}
