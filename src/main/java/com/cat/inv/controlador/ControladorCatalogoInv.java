package com.cat.inv.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.cat.inv.servicio.CatalogoInv;
import com.cat.inv.modelo.RespuestaMSCatalogo;
import com.cat.inv.modelo.PeticionCredenciales ;

@RestController
@RequestMapping("/Inventario/v1/Catalogos/Consulta")
@Tag(name = "Catálogo de Inventarios", description = "Operaciones relacionadas con catálogos")
public class ControladorCatalogoInv {
	
	@Autowired
	private CatalogoInv servicio ;
	
	@Operation(
			summary = "Consulta el catálogo",
	        description = "Recibe token y NIP, valida credenciales y devuelve el catálogo completo."
	)
	@PostMapping("/Catalogo")
    public RespuestaMSCatalogo obtenerCatalogoTipo( @RequestBody @Valid PeticionCredenciales credenciales ) {
        return servicio.obtenCatalogo( credenciales ) ;
    }
}