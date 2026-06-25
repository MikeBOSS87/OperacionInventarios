package com.cat.inv.servicio;

import com.cat.inv.modelo.PeticionCredenciales;
import com.cat.inv.modelo.RespuestaMSCatalogo;

public interface ServicioCatalogoInv {
	
	RespuestaMSCatalogo obtenCatalogo( PeticionCredenciales cred );
}