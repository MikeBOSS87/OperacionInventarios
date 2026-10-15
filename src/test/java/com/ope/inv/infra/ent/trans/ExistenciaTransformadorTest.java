package com.ope.inv.infra.ent.trans;

import com.ope.inv.dominio.excepciones.DatoInvalidoExcepcion;
import com.ope.inv.infra.ent.dto.DetalleProductoEntradaDTO;
import com.ope.inv.infra.ent.dto.ExistenciaEntradaDTO;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExistenciaTransformadorTest{
	
	private final ExistenciaTransformador transformador = new ExistenciaTransformador();

	@Test
    @DisplayName("Debería transformar correctamente la entrada con tipos Entrada y Salida")
    void testTransformarExitoso() {
        // --- 1. PREPARACIÓN (Given) ---
        ExistenciaEntradaDTO dto = new ExistenciaEntradaDTO();
        dto.setIdPersona("EMP-001");
        dto.setSuc("78314915-SUCURSAL"); // 14 caracteres (mayor a 8)
        dto.setFecha("13-10-2026 05:22:14"); // Formato requerido: dd-MM-yyyy HH:mm:ss

        DetalleProductoEntradaDTO detalle1 = new DetalleProductoEntradaDTO();
        detalle1.setIdUnidadMedida(1);
        detalle1.setSKU("SKU-001");
        detalle1.setTipo("Entrada"); // Cubre la rama 1 del switch
        detalle1.setCantidades( 10 );

        DetalleProductoEntradaDTO detalle2 = new DetalleProductoEntradaDTO();
        detalle2.setIdUnidadMedida(1);
        detalle2.setSKU("SKU-002");
        detalle2.setTipo("Salida"); // Cubre la rama -1 del switch
        detalle2.setCantidades( 5 );

        dto.setDetalleProductos( List.of( detalle1, detalle2 ) );

        // --- 2. EJECUCIÓN (When) ---
        var resultado = transformador.transformar(dto);

        // --- 3. VERIFICACIÓN (Then) ---
        assertNotNull(resultado);
        assertEquals("EMP-001", resultado.getId_Empleado());
        assertNotNull(resultado.getFecha_Doc());
        assertEquals(2, resultado.getDetalleDoc().size());
    }

    @Test
    @DisplayName("Debería lanzar excepción si la sucursal tiene menos de 8 caracteres")
    void testTransformarSucursalInvalida() {
        ExistenciaEntradaDTO dto = new ExistenciaEntradaDTO();
        dto.setSuc("CORTA"); // Menos de 8 caracteres

        assertThrows( DatoInvalidoExcepcion.class, () -> {
            transformador.transformar(dto);
        });
    }

    @Test
    @DisplayName("Debería lanzar excepción si el tipo de movimiento no es válido")
    void testTransformarTipoInvalido() {
        ExistenciaEntradaDTO dto = new ExistenciaEntradaDTO();
        dto.setSuc( "75904915-SUCURSAL-NORTE" );
        dto.setFecha( "13-10-2026 05:22:14" );

        DetalleProductoEntradaDTO detalle = new DetalleProductoEntradaDTO();
        detalle.setTipo("Indefinido"); // Valor no contemplado en el switch
        dto.setDetalleProductos( List.of( detalle ) );

        assertThrows( DatoInvalidoExcepcion.class, () -> {
            transformador.transformar(dto);
        } );
    }
}