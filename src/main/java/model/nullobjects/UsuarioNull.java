package model.nullobjects;

import model.Usuario;

public class UsuarioNull extends Usuario{
	
	public static Usuario build() {
		return new UsuarioNull();
	}
	
	public UsuarioNull() {
		super(0, "", null, 0, 0.0,"","", false,false);
	}
	
	public boolean isNull() {
		return true;
	}

}
