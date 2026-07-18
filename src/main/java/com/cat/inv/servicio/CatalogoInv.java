package com.cat.inv.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.cat.inv.modelo.Catalogo;
import com.cat.inv.modelo.MapearCatalogo;
import com.cat.inv.modelo.PeticionCredenciales;
import com.cat.inv.modelo.RespuestaMSCatalogo;
import com.cat.inv.repositorio.ConsultaCatalogo;

@Service
public class CatalogoInv implements ServicioCatalogoInv{
	
	private static final Logger loger = LoggerFactory.getLogger( CatalogoInv.class );
	
	@Autowired
    private ConsultaCatalogo repo;
	
	@Override
    public RespuestaMSCatalogo obtenCatalogo( PeticionCredenciales cred ) {
		
		loger.info( "🔐 Validando credenciales..." );
		
		if( !"MDRG25014915".equals( cred.getToken()) || !"2501".equals(cred.getNip()) ){

            loger.warn( "❌ Credenciales inválidas | token={} | nip={}", cred.getToken(), cred.getNip() );

            throw new IllegalArgumentException( "TOKEN o NIP incorrecto" );
        }

        loger.info( "✔ Credenciales válidas, consultando catálogo..." );
		
		List< Catalogo > lstCatalogo = repo.obtenerCatalogo()
			    .stream()
			    .map( MapearCatalogo::map )
			    .collect( Collectors.toList() );
		
		loger.info( "📤 Consulta completada. Registros obtenidos: {}", lstCatalogo.size() );
		
		return new RespuestaMSCatalogo( "Consulta realizada!!!", lstCatalogo ) ;
	}
}