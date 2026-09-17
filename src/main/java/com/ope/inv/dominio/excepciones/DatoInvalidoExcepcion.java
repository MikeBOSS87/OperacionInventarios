package com.ope.inv.dominio.excepciones;

public class DatoInvalidoExcepcion extends RuntimeException {
	
	public static final long serialVersionUID = 974169264165214659L;

    public DatoInvalidoExcepcion( String mensaje ) {
        super( mensaje );
    }
}