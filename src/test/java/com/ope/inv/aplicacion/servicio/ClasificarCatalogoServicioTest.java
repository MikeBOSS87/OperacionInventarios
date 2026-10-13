package com.ope.inv.aplicacion.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
	
	@SuppressWarnings({ "unchecked" })
	@Test
    @DisplayName("Debería consultar y catalogar la existencia correctamente")
	void testClasificarCatalogoExitoso() {
		
		// --- 1. PREPARACIÓN (Given) ---
	    // Prepara tus objetos de entrada y configura los mocks con when(...) o doNothing()
		Existencia existenciaMock = new Existencia();
        existenciaMock.setDetalleDoc( new ArrayList<DetalleExistencia>() );
        
        Map<String, Integer> skus = new HashMap<String, Integer>() ;
        skus.put( "SKU-00250100" , 2501 ) ;
        
        //Simulamos que consulte skus
        when( consultaProductoRepositorio.consultarPorSku( new ArrayList<String>() ) ).thenReturn( skus ) ;
        
	    // --- 2. EJECUCIÓN (When) ---
	    // Ejecuta el método de tu servicio
        clasificarCatalogoServicio.clasificar( existenciaMock ) ;
	
	    // --- 3. VERIFICACIÓN (Then) ---
	    // Valida que el resultado no sea nulo o que los mocks hayan sido llamados
        verify( consultaProductoRepositorio, times( 1 ) ).consultarPorSku( (List<String>) any( List.class ) ) ;
	}
}