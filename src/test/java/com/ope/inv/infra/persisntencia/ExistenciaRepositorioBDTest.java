package com.ope.inv.infra.persisntencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.infra.persistencia.ExistenciaRepositorioBD;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExistenciaRepositorioBDTest{
	
	@Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ExistenciaRepositorioBD existenciaRepositorioBD;

    @Test
    @DisplayName("Debería guardar la existencia y sus detalles usando JdbcTemplate")
    void testGuardarExistencia() {
        // --- 1. PREPARACIÓN (Given) ---
        Existencia existencia = new Existencia();
        existencia.setId_Empleado("EMP-001");
        existencia.setId_Sucursal(10);
        existencia.setFolio("FOLIO-123");
        existencia.setFecha_Doc(LocalDateTime.now());

        DetalleExistencia detalle = new DetalleExistencia();
        detalle.setId_Un_Medida(1);
        detalle.setId_SKU_Codigo("SKU-001");
        detalle.setId_Producto( 10 );
        detalle.setFechaDoc(LocalDateTime.now());
        detalle.setFolio( "FOLIO-123" );
        detalle.setId_Det( 11 );
        detalle.setTipo(1);
        detalle.setCantidad( BigDecimal.TWO );

        existencia.setDetalleDoc( List.of( detalle ) );

        // --- 2. EJECUCIÓN (When) & 3. VERIFICACIÓN (Then) ---
        assertDoesNotThrow(() -> existenciaRepositorioBD.Guardar(existencia));

        // Verifica que se ejecutó el método update de JdbcTemplate tanto para la cabecera como para los detalles
        verify(jdbcTemplate, atLeast(2)).update(anyString(), any(Object[].class));
    }
}