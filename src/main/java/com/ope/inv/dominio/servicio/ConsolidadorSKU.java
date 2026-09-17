package com.ope.inv.dominio.servicio;

import java.util.Map ;
import java.util.stream.Collectors;

import com.ope.inv.dominio.modelo.ClaveSKU;
import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.modelo.Existencia;

public class ConsolidadorSKU {
	
	public Existencia consolidar( Existencia e ) {
		
		Map<ClaveSKU, DetalleExistencia> detallesAgrupados = e.getDetalleDoc()
				.stream()
				.map( det -> {
					det.setFolio( e.getFolio() ) ;
					det.setFechaDoc( e.getFecha_Doc() ) ;
					return det ;
				})
				.collect(
					Collectors.toMap(
						det -> new ClaveSKU(
								det.getId_Obj_Alm(),
								det.getTipo()
						),
						det -> det,
						( det1, det2 ) -> {
							det1.setCantidad(
								det1.getCantidad().add( det2.getCantidad() )
							) ;
							return det1 ;
						}
					)
				) ;
		
		
		
		e.setDetalleDoc(
				detallesAgrupados.values()
				.stream()
				.toList()
		);
		return e ;
	}
}