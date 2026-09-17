package com.ope.inv.dominio.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import com.ope.inv.dominio.puertos.sal.ConsultaFolioRepositorio;

public class GeneradorFolio{
	
	private static final Logger loger =
	        LoggerFactory.getLogger( GeneradorFolio.class );
	private static final String PREFIJO = "AA";
	private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
    
	private final ConsultaFolioRepositorio repo ;
	
	public GeneradorFolio( ConsultaFolioRepositorio repo ) {
		this.repo = repo ;
	}
	
	public String generar() {
	    String ultimoFolio = repo.obtenerUltimoFolio();
	    loger.info(
	            "Último folio obtenido: {}",
	            ultimoFolio
	        );
	    
		Integer consecutivo = obtenerConsecutivo( ultimoFolio ) ;
		loger.info(
		        "Consecutivo calculado: {}",
		        consecutivo
		    );
		String fecha = LocalDateTime.now()
                       .format( FORMATO_FECHA );
        String aleatorioNumerico = generarNumeros();
        String aleatorioLetras = generarLetras();
	    
        loger.info( "Folio generado correctamente" );
	    return PREFIJO
                + fecha
                + String.format( "%06d", consecutivo )
                + aleatorioLetras
                + aleatorioNumerico ;
	}
	
	private Integer obtenerConsecutivo( String ultimoFolio ) {
        if( ultimoFolio == null || ultimoFolio.isBlank() ){
            return 1;
        }
        String parteNumerica = ultimoFolio.substring( 16, 22 );
        return Integer.parseInt( parteNumerica ) + 1 ;
    }
	
	private String generarNumeros() {
        return String.format( "%04d", 
        		ThreadLocalRandom.current().nextInt( 0, 10000 )
        );
    }
	
	private String generarLetras() {
        StringBuilder letras = new StringBuilder();
        for( Integer i = 0; i < 4; i++ ){
           Character letra =
                    (char) ThreadLocalRandom.current()
                    .nextInt('A', 'Z' + 1);

            letras.append( letra );
        }
        return letras.toString();
    }
}