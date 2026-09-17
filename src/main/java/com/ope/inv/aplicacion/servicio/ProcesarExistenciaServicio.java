package com.ope.inv.aplicacion.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.puertos.ent.ProcesarExistencia;
import com.ope.inv.dominio.puertos.sal.ExistenciaRepositorio;
import com.ope.inv.dominio.servicio.ConsolidadorSKU;
import com.ope.inv.dominio.servicio.GeneradorFolio;
import com.ope.inv.dominio.servicio.GeneradorIdDetalle;

public class ProcesarExistenciaServicio implements ProcesarExistencia {
	
	private static final Logger loger = LoggerFactory.getLogger( 
			ProcesarExistenciaServicio.class );
	
	private final GeneradorFolio generadorFolio;
    private final ConsolidadorSKU consolidadorSKU;
    private final GeneradorIdDetalle generadorIdDetalles ;
    private final ExistenciaRepositorio existenciaRepositorio ;  

    public ProcesarExistenciaServicio(
            GeneradorFolio generadorFolio,
            ConsolidadorSKU consolidadorSKU,
            GeneradorIdDetalle generadorIdDetalles,
            ExistenciaRepositorio er
    		){
        this.generadorFolio = generadorFolio;
        this.consolidadorSKU = consolidadorSKU;
        this.generadorIdDetalles = generadorIdDetalles;
        this.existenciaRepositorio = er ;
    }
    
    @Transactional
    public Existencia procesar( Existencia entrada ) {
    	loger.info(
    	        "Iniciando procesamiento de existencia. "
    	        + "Sucursal: {}, Detalles: {}",
    	        entrada.getId_Sucursal(),
    	        entrada.getDetalleDoc().size()
    			);

        String folio = generadorFolio.generar();

        entrada.setFolio( folio );
        
        loger.info(
                "Folio generado: {}",
                folio
            );
        loger.info( "Iniciando consolidación de SKU" );
        Existencia existenciaConsolidada =
                consolidadorSKU.consolidar( entrada );
        loger.info( "Consolidación de SKU completada" );
        
        loger.info( "Generando ID_DET" );
        Existencia existenciaProcesada =
                generadorIdDetalles.generarIdentitys(
                        existenciaConsolidada
                );
        
        loger.info( "Guardando trx." );
        existenciaRepositorio.Guardar( existenciaProcesada );
        
        loger.info(
                "Procesamiento completado con folio: {}",
                existenciaProcesada.getFolio()
            );
        return existenciaProcesada;
    }
}