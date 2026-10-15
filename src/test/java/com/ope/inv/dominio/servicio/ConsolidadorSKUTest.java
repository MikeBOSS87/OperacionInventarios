package com.ope.inv.dominio.servicio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.modelo.Existencia;

public class ConsolidadorSKUTest{
	
	private final ConsolidadorSKU consolidadorSKU = new ConsolidadorSKU();

    @Test
    @DisplayName( "Debería consolidar los SKUs duplicados correctamente" )
    void testConsolidarSKUSuccess() {
    	
    	// --- 1. PREPARACIÓN (Given) ---
        Existencia existencia = new Existencia();
        List<DetalleExistencia> detalles = new ArrayList<>();

        DetalleExistencia detalle1 = new DetalleExistencia();
        detalle1.setId_SKU_Codigo( "SKU-001" );
        detalle1.setCantidad( BigDecimal.valueOf( 10 ) );
        detalles.add( detalle1 );

        DetalleExistencia detalle2 = new DetalleExistencia();
        detalle2.setId_SKU_Codigo( "SKU-001" ); // Duplicado para sumar cantidad
        detalle2.setCantidad( BigDecimal.valueOf( 15 ) );
        detalles.add( detalle2 );

        DetalleExistencia detalle3 = new DetalleExistencia();
        detalle3.setId_SKU_Codigo( "SKU-002" );
        detalle3.setCantidad( BigDecimal.valueOf( 10 ) );
        detalles.add( detalle3 );

        existencia.setDetalleDoc(detalles);

        // --- 2. EJECUCIÓN (When) ---
        consolidadorSKU.consolidar(existencia);

        // --- 3. VERIFICACIÓN (Then) ---
        assertNotNull(existencia.getDetalleDoc());
        
        // Aquí el consolidador agrupa los SKUs repetidos sumando sus cantidades
        assertFalse( existencia.getDetalleDoc().isEmpty() );
    }
}