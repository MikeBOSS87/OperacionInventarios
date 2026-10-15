package com.ope.inv.infra.ent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.puertos.ent.ProcesarExistencia;
import com.ope.inv.infra.ent.dto.ExistenciaEntradaDTO;
import com.ope.inv.infra.ent.trans.ExistenciaTransformador;

@RestController
@RequestMapping("/cargaDatos/existencias")
@Tag(name = "Existencias", description = "Operaciones para registrar documentos de entrada y movimientos de inventario")
public class ExistenciaControlador {
	
	private static final Logger loger = LoggerFactory.getLogger(ExistenciaControlador.class);
	private final ProcesarExistencia procesarExistencia ;
	
	public ExistenciaControlador( ProcesarExistencia pe ) {
		this.procesarExistencia = pe ;
	}
	
	@PostMapping
    @Operation(
        summary = "Registrar una existencia",
        description = "Recibe un documento de inventario. Los detalles se consolidan por SKU y tipo de movimiento; los SKU encontrados en catálogo se procesan como detalles y los no encontrados se devuelven en DetalleNoCatalogado.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Datos de la existencia. La fecha debe usar el formato dd-MM-yyyy HH:mm:ss.",
            content = @Content(
                schema = @Schema(implementation = ExistenciaEntradaDTO.class),
                examples = @ExampleObject(name = "Ejemplo de entrada", value = "{\"fecha\":\"21-09-2026 11:03:09\",\"Id_Persona\":\"060987\",\"suc\":\"25010609-matriz\",\"detalleProductos\":[{\"Id_Uni_Medida\":1,\"SKU\":\"SKU-5002\",\"Tipo\":\"Entrada\",\"Cantidades\":1},{\"Id_Uni_Medida\":1,\"SKU\":\"SKU-010\",\"Tipo\":\"Entrada\",\"Cantidades\":4},{\"Id_Uni_Medida\":1,\"SKU\":\"SKU-5002\",\"Tipo\":\"Entrada\",\"Cantidades\":2}]}")
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Existencia procesada correctamente", content = @Content(schema = @Schema(implementation = Existencia.class))),
            @ApiResponse(responseCode = "400", description = "Petición o dato inválido", content = @Content(schema = @Schema(implementation = com.ope.inv.infra.excepciones.ErrorRespuesta.class))),
            @ApiResponse(responseCode = "500", description = "Error interno durante el procesamiento")
        }
    )
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