package com.cat.inv.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cat.inv.modelo.RespuestaError;

@RestControllerAdvice
public class ManejoGlobalExcepciones {
	
	private static final Logger loger = LoggerFactory.getLogger( ManejoGlobalExcepciones.class );
	
	// Errores de Oracle / BD
    @ExceptionHandler( DataAccessException.class )
    public ResponseEntity< RespuestaError > manejarErrorBD( DataAccessException ex ) {

        loger.error("❌ Error de base de datos: {}", ex.getMessage(), ex);

        RespuestaError error = new RespuestaError(
        		"Error al consultar la base de datos" + ex.getMostSpecificCause().getMessage()
        );

        return new ResponseEntity<>( error, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    
    // NullPointer y errores inesperados
    @ExceptionHandler( NullPointerException.class )
    public ResponseEntity< RespuestaError > manejarNull( NullPointerException ex ) {

        loger.error("❌ NullPointer detectado: {}", ex.getMessage(), ex);

        RespuestaError error = new RespuestaError(
                "Referencia nula detectada"
        );

        return new ResponseEntity<>( error, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    
    // Cualquier otro error
    @ExceptionHandler( Exception.class )
    public ResponseEntity< RespuestaError > manejarGeneral( Exception ex ) {

        loger.error( "❌ Error inesperado: {}", ex.getMessage(), ex );

        RespuestaError error = new RespuestaError(
                "Ocurrio algo " + ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}