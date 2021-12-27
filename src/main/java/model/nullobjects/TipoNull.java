package model.nullobjects;

import model.Tipo;

public class TipoNull extends Tipo {
	public static TipoNull build() {
		return new TipoNull();
	}
	
	public TipoNull() {
		super(0, "","",false,"");
	}
	
	public boolean isNull() {
		return true;
	}
}
