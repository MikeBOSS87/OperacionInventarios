package com.ope.inv.infra.ent.trans;

import java.util.List ;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.excepciones.DatoInvalidoExcepcion;
import com.ope.inv.infra.ent.dto.ExistenciaEntradaDTO;
import com.ope.inv.infra.ent.dto.DetalleProductoEntradaDTO;

public class ExistenciaTransformador {
	
	private static final Logger loger =
	        LoggerFactory.getLogger( ExistenciaTransformador.class );
	
	private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );
	
	public Existencia transformar( ExistenciaEntradaDTO EE ) {
		loger.info( "Transformando información recibida del cliente" );
		
		Existencia existencia = new Existencia();
		existencia.setId_Empleado( EE.getIdPersona() );
		existencia.setId_Sucursal( Integer.parseInt( 
				obtenerIdSucursal( EE.getSuc() ) 
				) );
		existencia.setFecha_Doc( convertirFecha( EE.getFecha() ) );
		existencia.setDetalleDoc( transformarDetalles( 
				EE.getDetalleProductos() 
				) );
		
		loger.info(
		        "Transformación completada. Sucursal: {}, Detalles: {}",
		        existencia.getId_Sucursal(),
		        existencia.getDetalleDoc().size()
		    );
		return existencia ;
	}
	
	private LocalDateTime convertirFecha( String fecha ) {
        return LocalDateTime.parse(
                fecha,
                FORMATO_FECHA
        );
    }
	
    private String obtenerIdSucursal( String sucursal ){
        if( sucursal == null || sucursal.length() < 8 ){
            throw new DatoInvalidoExcepcion(
                    "La sucursal debe contener al menos 8 caracteres"
            );
        }
        return sucursal.substring( 0, 8 );
    }
    
    private Integer convertirTipo( String tipo ) {

        return switch ( tipo ) {

            case "Entrada" -> 1;

            case "Salida" -> -1;

            default -> throw new DatoInvalidoExcepcion(
                    "Tipo de movimiento no válido: " + tipo
            );
        };
    }
    
    private DetalleExistencia transformarDetalle(
    		DetalleProductoEntradaDTO detalleEntrada
    		) {
    	DetalleExistencia detalleProducto = new DetalleExistencia() ;
    	
    	detalleProducto.setId_Un_Medida(
    			detalleEntrada.getIdUnidadMedida() 
    	);
    	detalleProducto.setId_SKU_Codigo(
                detalleEntrada.getSKU()
        );
    	detalleProducto.setId_Obj_Alm(
        		detalleEntrada.getIdProducto()
        );
    	
    	detalleProducto.setTipo(
                convertirTipo( detalleEntrada.getTipo() )
        );
    	detalleProducto.setCantidad(
        		BigDecimal.valueOf( detalleEntrada.getCantidades() )
        );

        return detalleProducto ;
    }
    
    
	private List< DetalleExistencia > transformarDetalles( 
    		List< DetalleProductoEntradaDTO > detallesEntrada 
    		){
    	
    	return detallesEntrada.stream()
    			.map( this::transformarDetalle )
    			.toList() ;
    }
}