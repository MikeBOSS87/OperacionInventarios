package com.ope.inv.dominio.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ModeloTest{
	
	@Test
    @DisplayName("Debería cubrir getters y setters de los modelos de dominio")
    void testGettersYSettersModelos() {
        
		Existencia ex = new Existencia();
        ex.setDetalleDoc( new ArrayList<>() );
        assertNotNull( ex.getDetalleDoc() );

        DetalleExistencia det = new DetalleExistencia();
        det.setId_SKU_Codigo( "SKU-74982" );
        det.setCantidad( BigDecimal.valueOf( 20 ) );
        
        assertEquals( "SKU-74982", det.getId_SKU_Codigo() );
        assertEquals( BigDecimal.valueOf( 20 ), det.getCantidad() );
        
        noCatalogados nc = new noCatalogados( "SKU-7843", BigDecimal.valueOf( 3 ) );
        nc.setCantidad( BigDecimal.valueOf( 10 ) );
        assertNotNull( nc );
    }
}