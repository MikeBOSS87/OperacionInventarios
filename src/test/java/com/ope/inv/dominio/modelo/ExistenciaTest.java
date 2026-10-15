package com.ope.inv.dominio.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExistenciaTest {
	
	@Test
    @DisplayName("Debería probar todos los getters y setters de Existencia")
    void testGettersYSettersExistencia() {
        // --- 1. PREPARACIÓN Y EJECUCIÓN (Given & When) ---
        Existencia existencia = new Existencia();

        String idEmpleado = "EMP-001";
        Integer idSucursal = 5;
        LocalDateTime fechaDoc = LocalDateTime.now();
        String folio = "FOLIO-12345";
        List<DetalleExistencia> detalleDoc = new ArrayList<>();
        List<noCatalogados> detalleNoCatalogado = new ArrayList<>();

        existencia.setId_Empleado(idEmpleado);
        existencia.setId_Sucursal(idSucursal);
        existencia.setFecha_Doc(fechaDoc);
        existencia.setFolio(folio);
        existencia.setDetalleDoc(detalleDoc);
        existencia.setDetalleNoCatalogado(detalleNoCatalogado);

        // --- 3. VERIFICACIÓN (Then) ---
        assertEquals(idEmpleado, existencia.getId_Empleado());
        assertEquals(idSucursal, existencia.getId_Sucursal());
        assertEquals(fechaDoc, existencia.getFecha_Doc());
        assertEquals(folio, existencia.getFolio());
        assertEquals(detalleDoc, existencia.getDetalleDoc());
        assertEquals(detalleNoCatalogado, existencia.getDetalleNoCatalogado());
    }
}