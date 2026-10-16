package com.ope.inv.dominio.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ModeloTest{
	
	@Test
    @DisplayName("Debería cubrir getters y setters de los modelos de dominio")
    void testGettersYSettersModelos() {
        String folio = "FOLIO-01" ;
		LocalDateTime fechaDoc = LocalDateTime.now();
		
		// Existencia
        Existencia ex = new Existencia();
        ex.setDetalleDoc(new ArrayList<>());
        ex.setDetalleNoCatalogado(new ArrayList<>());
        ex.setFolio( folio );
        ex.setId_Empleado("EMP-1");
        ex.setId_Sucursal(10);
        ex.setFecha_Doc( fechaDoc );
        
        assertNotNull(ex.getDetalleDoc());
        assertNotNull(ex.getDetalleNoCatalogado());
        assertEquals("FOLIO-01", ex.getFolio());
        assertEquals("EMP-1", ex.getId_Empleado());
        assertEquals(10, ex.getId_Sucursal());

        
        // DetalleExistencia
        DetalleExistencia det = new DetalleExistencia();
        det.setFolio(folio);
        det.setFechaDoc(fechaDoc);
        det.setId_SKU_Codigo( "SKU-TEST" );
        det.setCantidad( BigDecimal.TEN );
        det.setId_Producto( 5 );
        det.setId_Un_Medida( 2 );
        det.setTipo( 1 );
        
        assertEquals( folio, det.getFolio() ) ; 
        assertEquals( fechaDoc, det.getFechaDoc() ) ;
        assertEquals("SKU-TEST", det.getId_SKU_Codigo() );
        assertEquals(BigDecimal.TEN, det.getCantidad() );
        assertEquals( 5, det.getId_Producto() );
        assertEquals( 2, det.getId_Un_Medida() );
        assertEquals( 1, det.getTipo() );

        // No catalogados
        noCatalogados nc = new noCatalogados( "SKU-TEST-NoExiste", BigDecimal.TWO );
        nc.setId_SKU_Codigo("SKU-NC-01");
        nc.setCantidad( BigDecimal.valueOf(5) ) ; 
        
        assertNotNull(nc);
        assertEquals( "SKU-NC-01", nc.getId_SKU_Codigo() );
        assertEquals(BigDecimal.valueOf(5), nc.getCantidad());
    }
}