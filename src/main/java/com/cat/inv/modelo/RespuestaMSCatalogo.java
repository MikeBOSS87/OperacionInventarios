package com.cat.inv.modelo;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import io.swagger.v3.oas.annotations.media.Schema;

public class RespuestaMSCatalogo {
	
	@Schema(description = "Fecha de la respuesta", example = "2026-06-21 15:32:26")
	private String fecha;
	
	@Schema(description = "Mensaje de la operación", example = "Consulta realizada!!")
    private String mensaje;
	
	@Schema(description = "Lista de elementos del catálogo")
    private List< Catalogo > catalogo;
    
	public RespuestaMSCatalogo( String mensaje, List< Catalogo > catalogo ) {
		this.fecha = LocalDateTime.now().format( DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" ) );
		this.mensaje = mensaje;
		this.catalogo = catalogo;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @return the catalogo
	 */
	public List< Catalogo > getCatalogo() {
		return catalogo;
	}
}