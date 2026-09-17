package com.ope.inv.infra.ent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.puertos.ent.ProcesarExistencia;
import com.ope.inv.infra.ent.dto.ExistenciaEntradaDTO;
import com.ope.inv.infra.ent.trans.ExistenciaTransformador;

@RestController
@RequestMapping("/cargaDatos/existencias")
public class ExistenciaControlador {
	
	private static final Logger loger = LoggerFactory.getLogger(ExistenciaControlador.class);
	private final ProcesarExistencia procesarExistencia ;
	
	public ExistenciaControlador( ProcesarExistencia pe ) {
		this.procesarExistencia = pe ;
	}
	
	@PostMapping
	public Existencia procesar( @RequestBody ExistenciaEntradaDTO entrada ) {
		loger.info( "Inicio procesamiento de existencia" );
		ExistenciaTransformador transforma = new ExistenciaTransformador() ;
		
		Existencia existencia = transforma.transformar( entrada ) ;
		loger.info(
		        "Solicitud transformada. Usuario: {}, Sucursal: {}, Detalles: {}",
		        existencia.getId_Empleado(),
		        existencia.getId_Sucursal(),
		        existencia.getDetalleDoc().size()
		    );
		
		Existencia doc = procesarExistencia.procesar( existencia ) ;
		loger.info(
		        "Existencia procesada correctamente."
		    );

		return doc ;
	}
}