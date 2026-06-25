package com.cat.inv.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RespuestaError{
	
	private String fecha;
    private String mensaje;
    private Object catalogo;
    
    public RespuestaError( String mensaje ){
        this.fecha = LocalDateTime.now().format( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) );
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Object getCatalogo() {
        return catalogo;
    }
}