package com.ope.inv.conf;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class FiltroPeticionesTest{
	
	@Test
    @DisplayName("Debería ejecutar el filtro de peticiones correctamente")
    void testFiltroPeticiones() throws Exception {
        FiltroPeticiones filtro = new FiltroPeticiones();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/api/inventario");

        filtro.doFilter(request, response, filterChain);

        // Verifica que la cadena de filtros continúe
        verify(filterChain, times(1)).doFilter(request, response);
    }
}