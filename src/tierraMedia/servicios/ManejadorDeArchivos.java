package tierraMedia.servicios;

import tierraMedia.atracciones.Atraccion;
import tierraMedia.atracciones.TipoAtraccion;
import tierraMedia.Usuario;
import tierraMedia.promociones.PromoAbsoluta;
import tierraMedia.promociones.PromoAxB;
import tierraMedia.promociones.PromoPorcentual;
import tierraMedia.promociones.Promocion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorDeArchivos {

    public static List<Usuario> cargarUsuarios(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea != null)) {
                String[] datos = linea.split(";");
                String nombre = datos[0];
                String preferencia = datos[1];
                int monedas = Integer.parseInt(datos[2]);
                int tiempo = Integer.parseInt(datos[3]);

                usuarios.add(new Usuario(nombre, preferencia, monedas, tiempo));
                linea = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return usuarios;
        }
    }

    public static List<Atraccion> cargarAtracciones(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        List<Atraccion> atracciones = new ArrayList<>();

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea != null)) {
                String[] datos = linea.split(";");
                String nombre = datos[0];
                int costo = Integer.parseInt(datos[1]);
                double tiempo = Double.parseDouble(datos[2]);
                int cupoDiario = Integer.parseInt(datos[3]);
                String tipo = datos[4];

                atracciones.add(new Atraccion(nombre, costo, tiempo, cupoDiario, tipo));
                linea = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return atracciones;
        }

    }

    public static List<Promocion> cargarPromociones(String path, List<Atraccion> atracciones) {
        FileReader fr = null;
        BufferedReader br = null;
        List<Promocion> promociones = new ArrayList<>();

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea != null)) {
                String[] datos = linea.split(";");
                String nombre = datos[0];
                TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(datos[1].toUpperCase());
                String atr = datos[2];
                String[] atraccionesStr = atr.split(",");
                List<Atraccion> atraccionesPromo = ManejadorDeArchivos.atraccionesStrToList(atraccionesStr, atracciones);
                String tipoPromo = datos[3];


                if (datos[3].equals("absoluta")) {
                    int monedas = Integer.parseInt(datos[4]);
                    promociones.add(new PromoAbsoluta(nombre, tipoAtraccion, atraccionesPromo, monedas));
                } else if (tipoPromo.equals("porcentual")) {
                    int porcentaje = Integer.parseInt(datos[4]);
                    promociones.add(new PromoPorcentual(nombre, tipoAtraccion, atraccionesPromo, porcentaje));
                } else if (tipoPromo.equals("AxB")) {
                    Atraccion atraccion = ManejadorDeArchivos.obtenerAtraccionDesdeNombre(datos[4], atracciones);
                    promociones.add(new PromoAxB(nombre, tipoAtraccion, atraccionesPromo, atraccion));
                } else {
                    throw new Error("Tipo de promoción incorrecto.");
                }

                linea = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return promociones;
        }
    }

    public static List<Atraccion> atraccionesStrToList(String[] atraccionesStrings, List<Atraccion> atracciones) {
        List<Atraccion> atraccionesObj = new ArrayList<>();

        for (int i = 0; i < atraccionesStrings.length; i++) {
            atraccionesObj.add(ManejadorDeArchivos.obtenerAtraccionDesdeNombre(atraccionesStrings[i], atracciones));
        }
        return atraccionesObj;
    }

    public static Atraccion obtenerAtraccionDesdeNombre(String nombre, List<Atraccion> atracciones) {
        for (int i = 0; i < atracciones.size(); i++) {
            if (atracciones.get(i).getNombre().equals(nombre)) {
                return atracciones.get(i);
            }
        }
        return null;
    }
}