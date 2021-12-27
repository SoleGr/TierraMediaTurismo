package services;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import api.Frase;
import api.Frases;
import api.InfoLOTR;
import model.Atraccion;
import model.Promocion;
import persistence.commons.DAOFactory;

public class InicioService {
	
	public String getFrase() throws IOException, InterruptedException {
		Frases info = InfoLOTR.getFrase();
		return info.getDocs().get(0).getDialog();
	}
	
	public String getAutor() throws IOException, InterruptedException {
		Frase frase = InfoLOTR.getFrase().getDocs().get(0);
		String autorId = frase.getCharacter();
		String autor = InfoLOTR.getAutor(autorId);
		return autor;
	}

	public List<Promocion> getPromociones() {
		List<Promocion> promociones = DAOFactory.getPromocionDAO().obtenerTodos();
		Collections.sort(promociones);
		return promociones;
	}

	public List<Atraccion> getAtracciones() {
		List<Atraccion> atracciones = DAOFactory.getAtraccionDAO().obtenerTodos();
		Collections.sort(atracciones);
		return atracciones;
	}
	
}
