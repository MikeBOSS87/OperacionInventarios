package com.ope.inv.infra.excepciones;

public class ErrorRespuesta{
	
	private String codigo;
    private String mensaje;
    
	public ErrorRespuesta( String codigo, String mensaje ) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
}