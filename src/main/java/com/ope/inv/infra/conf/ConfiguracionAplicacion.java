package com.ope.inv.infra.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ope.inv.aplicacion.servicio.ClasificarCatalogoServicio;
import com.ope.inv.aplicacion.servicio.ProcesarExistenciaServicio;
import com.ope.inv.dominio.puertos.ent.ProcesarExistencia;
import com.ope.inv.dominio.puertos.sal.ConsultaFolioRepositorio;
import com.ope.inv.dominio.puertos.sal.ConsultaProductoRepositorio;
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
	GeneradorFolio generadorFolio( ConsultaFolioRepositorio consultaFolio ) {
		return new GeneradorFolio( consultaFolio ) ;
	}
	
	@Bean
    ClasificarCatalogoServicio clasificarCatalogoServicio(
            ConsultaProductoRepositorio consultaProducto 
    		) {
        return new ClasificarCatalogoServicio(
                consultaProducto
        );
    }
	
	@Bean ProcesarExistencia procesarExistenciaServicio(
            GeneradorFolio generarFolio,
            ConsolidadorSKU consolidadorSKU,
            ClasificarCatalogoServicio consultaProductoRepositorio,
            GeneradorIdDetalle generadorIdDetalle,
            ExistenciaRepositorio existenciaRepositorio
			) {

        return new ProcesarExistenciaServicio(
                generarFolio,
                consolidadorSKU,
                consultaProductoRepositorio,
                generadorIdDetalle,
                existenciaRepositorio
        );
	}
}