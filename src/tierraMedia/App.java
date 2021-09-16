package tierraMedia;

import tierraMedia.servicios.GestorDeSugerencias;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        GestorDeSugerencias gestor = new GestorDeSugerencias();
        gestor.cargarUsuarios("entrada/usuarios.txt");
        gestor.cargarProductos("entrada/atracciones.txt","entrada/promociones.txt");
        gestor.generarSugerenciasParaUsuarios();
    }
}
