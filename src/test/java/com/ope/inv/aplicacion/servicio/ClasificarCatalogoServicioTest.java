package com.ope.inv.aplicacion.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.puertos.sal.ConsultaProductoRepositorio;

@ExtendWith( MockitoExtension.class )
public class ClasificarCatalogoServicioTest {
	
	@Mock
	private ConsultaProductoRepositorio consultaProductoRepositorio;
	
	@InjectMocks
	private ClasificarCatalogoServicio clasificarCatalogoServicio ;
	
	@Test
    @DisplayName("Debería consultar y catalogar la existencia correctamente")
	void testClasificarCatalogoExitoso() {
		
		Existencia existenciaMock = new Existencia();
        DetalleExistencia detalle = new DetalleExistencia();
        detalle.setId_SKU_Codigo("SKU-INEXISTENTE");
        existenciaMock.setDetalleDoc(List.of(detalle));
        existenciaMock.setDetalleNoCatalogado(new ArrayList<>());

        // Simulamos que el repositorio regresa un mapa vacío (ningún SKU coincide)
        when(consultaProductoRepositorio.consultarPorSku(any())).thenReturn(new HashMap<>());

        clasificarCatalogoServicio.clasificar(existenciaMock);

        assertNotNull(existenciaMock.getDetalleNoCatalogado());
	}
}