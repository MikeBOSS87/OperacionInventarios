package com.ope.inv.infra.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ope.inv.dominio.excepciones.DatoInvalidoExcepcion;

@RestControllerAdvice
public class ManejadorExcepciones {
	
	private static final Logger loger =
            LoggerFactory.getLogger( ManejadorExcepciones.class );
	
	@ExceptionHandler( DatoInvalidoExcepcion.class )
	public ResponseEntity< ErrorRespuesta > manejarDatoInvalido( 
			DatoInvalidoExcepcion msj ) {
		
		loger.error("Dato inválido recibido: {}", msj.getMessage());
        ErrorRespuesta respuesta = new ErrorRespuesta(
                "DATO_INVALIDO",
                msj.getMessage()
        );
        return ResponseEntity
                .badRequest()
                .body( respuesta );
    }
	
	@ExceptionHandler( HttpMessageNotReadableException.class )
	public ResponseEntity< ErrorRespuesta > manejarJsonInvalido( 
			HttpMessageNotReadableException msj
			) {
		
		loger.error("JSON inválido recibido", msj );
		ErrorRespuesta er = new ErrorRespuesta( 
				"JSON_INVALIDO"
				, "El formato de los datos no es valido" 
		) ;
		return ResponseEntity
				.badRequest()
				.body( er ) ;
	}
}