package com.ope.inv.infra.persisntencia;

import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows ;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals ;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ope.inv.dominio.excepciones.DatoInvalidoExcepcion;
import com.ope.inv.infra.persistencia.ConsultaProductoRepositorioBD;

@ExtendWith(MockitoExtension.class)
public class ConsultaProductoRepositorioBDTest {

    @Mock
    private NamedParameterJdbcTemplate ejecutor;

    @InjectMocks
    private ConsultaProductoRepositorioBD consultaProductoRepositorioBD;

    @Test
    @DisplayName("Debería retornar mapa vacío si la lista de SKUs es nula o vacía")
    void testConsultarPorSkuVacio() {
        // Lista nula
        Map<String, Integer> resultadoNulo = consultaProductoRepositorioBD.consultarPorSku(null);
        assertTrue(resultadoNulo.isEmpty());

        // Lista vacía
        Map<String, Integer> resultadoVacio = consultaProductoRepositorioBD.consultarPorSku(List.of());
        assertTrue(resultadoVacio.isEmpty());
    }

    @Test
    @DisplayName("Debería consultar y mapear los SKUs correctamente")
    @SuppressWarnings("unchecked")
    void testConsultarPorSkuExitoso() throws SQLException {
        List<String> skus = List.of("SKU-001", "SKU-002");
        ResultSet rs = mock(ResultSet.class);
        
        // Simulamos 2 registros en el ResultSet y luego termina
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getString("ID_SKU_CODIGO")).thenReturn("SKU-001", "SKU-002");
        when(rs.getInt("ID_PRODUCTO")).thenReturn(10, 20);

        // Interceptamos la llamada al query para ejecutar el ResultSetExtractor interno
        when(ejecutor.query(anyString(), any(Map.class), any(ResultSetExtractor.class)))
                .thenAnswer(invocation -> {
                    ResultSetExtractor<Map<String, Integer>> extractor = invocation.getArgument(2);
                    return extractor.extractData(rs);
                });

        Map<String, Integer> resultado = consultaProductoRepositorioBD.consultarPorSku(skus);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(10, resultado.get("SKU-001"));
        assertEquals(20, resultado.get("SKU-002"));
    }

    @Test
    @DisplayName("Debería lanzar excepción si hay SKUs duplicados en el catálogo")
    @SuppressWarnings("unchecked")
    void testConsultarPorSkuDuplicadoExcepcion() throws SQLException {
        List<String> skus = List.of("SKU-DUP");

        ResultSet rs = mock(ResultSet.class);
        // Simulamos dos filas con el mismo SKU para activar la excepción
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getString("ID_SKU_CODIGO")).thenReturn("SKU-DUP", "SKU-DUP");
        when(rs.getInt("ID_PRODUCTO")).thenReturn(10, 20);

        when(ejecutor.query(anyString(), any(Map.class), any(ResultSetExtractor.class)))
                .thenAnswer(invocation -> {
                    ResultSetExtractor<Map<String, Integer>> extractor = invocation.getArgument(2);
                    return extractor.extractData(rs);
                });

        assertThrows( DatoInvalidoExcepcion.class, () -> {
            consultaProductoRepositorioBD.consultarPorSku(skus);
        });
    }
}