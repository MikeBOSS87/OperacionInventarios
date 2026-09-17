package com.ope.inv.infra.persistencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ope.inv.dominio.puertos.sal.ConsultaFolioRepositorio;

@Repository
public class ConsultaFolioRepositorioBD implements ConsultaFolioRepositorio {

	private static final Logger loger = LoggerFactory.getLogger(
	                	ConsultaFolioRepositorioBD.class
	                );
	private final JdbcTemplate templeit ;
	
	public ConsultaFolioRepositorioBD( JdbcTemplate template ) {
		this.templeit = template ;
	}
	
	@Override
	public String obtenerUltimoFolio() {
		loger.debug(
		        "Consultando último folio de la tabla"
			    );
		
		String sql = """
                SELECT MAX( FOLIO )
                FROM INV_O_EXISTENCIAS E
                """;
		
		String folio = templeit.queryForObject( sql, String.class ) ;
		loger.debug(
		        "Consulta de último folio completada"
			    );
		return folio ;
	}
}