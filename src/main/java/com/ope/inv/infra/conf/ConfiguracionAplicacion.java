package com.ope.inv.infra.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ope.inv.aplicacion.servicio.ProcesarExistenciaServicio;
import com.ope.inv.dominio.puertos.ent.ProcesarExistencia;
import com.ope.inv.dominio.puertos.sal.ConsultaFolioRepositorio;
import com.ope.inv.dominio.puertos.sal.ExistenciaRepositorio;
import com.ope.inv.dominio.servicio.ConsolidadorSKU;
import com.ope.inv.dominio.servicio.GeneradorFolio;
import com.ope.inv.dominio.servicio.GeneradorIdDetalle;

@Configuration
public class ConfiguracionAplicacion {
	
	@Bean GeneradorIdDetalle generadorIdDetalle() {
		return new GeneradorIdDetalle() ;
	}
	
	@Bean ConsolidadorSKU consilidadorSKU() {
		return new ConsolidadorSKU() ;
	}
	
	@Bean
	public GeneradorFolio generadorFolio( ConsultaFolioRepositorio repo ) {
		return new GeneradorFolio( repo ) ;
	}
	
	@Bean ProcesarExistencia procesarExistenciaServicio(
            GeneradorFolio generadorFolio,
            ConsolidadorSKU consolidadorSKU,
            GeneradorIdDetalle generadorIdDetalle,
            ExistenciaRepositorio existenciaRepositorio
			) {

        return new ProcesarExistenciaServicio(
                generadorFolio,
                consolidadorSKU,
                generadorIdDetalle,
                existenciaRepositorio
        );
	}
}