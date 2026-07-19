package com.cat.inv.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.cat.inv.servicio.AdminCatalogos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/crud/cat/inv" )
@Tag( name = "CRUD de Inventarios", description = "Donde se realizan la alta y actualizacion del dato del catalogo" )
public class ControladorPeticionesInv {
	
	@Autowired
	private AdminCatalogos AC ;
	
	@Operation(
			summary = "CRUD del tipo de catálogo y  el catalogo de inventarios",
	        description = "Recibe token y NIP, valida credenciales y deja ejecutar el SP con el JSON formado."
	)
	@PostMapping("/cargar")
    public String procesarCatalogo( @RequestBody String jsonRecibido ){
		
        String respuesta = AC.procesarPeticion( jsonRecibido );
        
        if( respuesta.startsWith( "Error" ) ){
        	return respuesta ;
        }
        
        return respuesta ;
	}
}