package com.ope.inv.dominio.servicio;

import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.modelo.Existencia;

public class GeneradorIdDetalle {
   
	public Existencia generarIdentitys( Existencia E ) {
		
		Integer numeracion = 1 ;
		for( DetalleExistencia det: E.getDetalleDoc() ) {
			det.setId_Det( numeracion );
			numeracion++ ;
		}
		return E ;
	}
}