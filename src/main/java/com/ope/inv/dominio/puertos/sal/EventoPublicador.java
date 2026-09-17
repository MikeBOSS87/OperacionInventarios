package com.ope.inv.dominio.puertos.sal;

import com.ope.inv.dominio.modelo.Existencia;

public interface EventoPublicador {
	
	void publicar( Existencia E ) ;
}