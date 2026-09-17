package com.ope.inv.aplicacion.configuraciones;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class FiltroPeticiones implements Filter{
	
	private static final Logger loger = LoggerFactory.getLogger( FiltroPeticiones.class );

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
		
		HttpServletRequest req = ( HttpServletRequest ) request;

        // Generar ID único por request
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        String ip = req.getRemoteAddr();
        String metodo = req.getMethod();
        String endpoint = req.getRequestURI();

        loger.info("♠ Nueva petición | IP={} | Método={} | Endpoint={} | RequestId={}", ip, metodo, endpoint, requestId);

        long inicio = System.currentTimeMillis();

        chain.doFilter( request, response );

        long fin = System.currentTimeMillis();
        long tiempo = fin - inicio;

        loger.info("♣ Petición finalizada | RequestId={} | Tiempo={} ms", requestId, tiempo);

        MDC.clear();
	}
}