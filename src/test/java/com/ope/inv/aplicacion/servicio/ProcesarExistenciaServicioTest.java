package com.ope.inv.aplicacion.servicio;

import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.modelo.noCatalogados;
import com.ope.inv.dominio.puertos.sal.ExistenciaRepositorio;
import com.ope.inv.dominio.servicio.ConsolidadorSKU;
import com.ope.inv.dominio.servicio.GeneradorFolio;
import com.ope.inv.dominio.servicio.GeneradorIdDetalle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@ExtendWith( MockitoExtension.class )
public class ProcesarExistenciaServicioTest{
	
	// 1. Mock de la base de datos / repositorio
    @Mock
    private ExistenciaRepositorio existenciaRepositorio;

    // 2. Mock del servicio de dominio que genera folios (¡Este faltaba!)
    @Mock
    private GeneradorFolio generadorFolio;
    
    // <-- Mock del Consolidador
    @Mock
    private ConsolidadorSKU consolidadorSKU;
    
    // <-- Mock de los Identitys
    @Mock
    private GeneradorIdDetalle IdentityProducto ;
    
    @Mock
    private ClasificarCatalogoServicio ccs;

    // Inyecta ambos mocks en el servicio principal
    @InjectMocks
    private ProcesarExistenciaServicio procesarExistenciaServicio;

    @Test
    @DisplayName( "Debería procesar y guardar la existencia correctamente" )
    void testProcesarExistenciaExitoso() {

    	// --- 1. PREPARACIÓN (Given) ---
        Existencia existenciaMock = new Existencia();
        existenciaMock.setDetalleDoc( new ArrayList<DetalleExistencia>() );
        existenciaMock.setDetalleNoCatalogado( new ArrayList<noCatalogados>() );
        
        // Configuramos el mock para que no haga nada al guardar
        doNothing().when( existenciaRepositorio ).Guardar( any( Existencia.class ) );
        
        // Configuramos el mock para que no consolide
        when( consolidadorSKU.consolidar( any( Existencia.class ) ) ).thenReturn( existenciaMock ) ;
                
        // Simulamos que el generador de folios devuelve un texto cuando se lo invoque
        when( generadorFolio.generar() ).thenReturn( "AA06092026035606002510MDRG4915" );
        
        // Simulamos que agrege una numeracion a un producto
        when( IdentityProducto.generarIdentitys( any( Existencia.class ) ) ).thenReturn( existenciaMock ) ;
        
        // Simulamos que clasifique un producto
        when( ccs.clasificar( any( Existencia.class ) ) ).thenReturn( existenciaMock ) ;
        
        // --- 2. EJECUCIÓN (When) ---
        procesarExistenciaServicio.procesar( existenciaMock );

        // --- 3. VERIFICACIÓN (Then - Opcional pero recomendado) ---
        // Verificamos que realmente se haya mandado a llamar al repositorio
        verify( existenciaRepositorio, times( 1 ) ).Guardar( any( Existencia.class ) );
        verify( consolidadorSKU, times( 1 ) ).consolidar( existenciaMock ) ;
        verify( generadorFolio, times( 1 ) ).generar() ;
    }
}