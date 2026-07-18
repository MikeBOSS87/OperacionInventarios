package com.cat.inv.repositorio;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import jakarta.annotation.PostConstruct;

@Repository
public class ConsultaCatalogo {
	
   private static final Logger loger = LoggerFactory.getLogger( ConsultaCatalogo.class );
	
   @Autowired
   private JdbcTemplate jdbcTemplate;
	
   private SimpleJdbcCall spConsultaCat;
   
   @PostConstruct
   private void init() {
      spConsultaCat = new SimpleJdbcCall( jdbcTemplate )
            .withSchemaName( "USROPE" )                 // <-- cámbialo si tu SP está en otro schema
	        .withCatalogName( "INV_CTL_CATALOGOS" )     // <-- nombre del PACKAGE
	        .withProcedureName( "CONSULTA_CAT" );       // <-- nombre del SP
   }
    
   public List< Map< String, Object > > obtenerCatalogo(){
	  loger.info( "Ejecutando SP de consulta a la BD" );
      Map< String, Object > resultado = spConsultaCat.execute();
      return( List< Map< String, Object > > ) resultado.get( "PA_CATALOGO" );
   }
}