package com.ope.inv.infra.persistencia;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ope.inv.dominio.excepciones.DatoInvalidoExcepcion;
import com.ope.inv.dominio.puertos.sal.ConsultaProductoRepositorio;

@Repository
public class ConsultaProductoRepositorioBD implements ConsultaProductoRepositorio {

	private static final Logger loger =
            LoggerFactory.getLogger( ConsultaProductoRepositorioBD.class );

    private final NamedParameterJdbcTemplate ejecutor ;

    public ConsultaProductoRepositorioBD( NamedParameterJdbcTemplate template ){
        this.ejecutor = template;
    }
	
	@Override
	public Map<String, Integer> consultarPorSku( List< String > lstskus ){
		
		if( lstskus == null || lstskus.isEmpty()) {
            return Collections.emptyMap();
        }

		loger.debug(
                "Consultando catálogo para {} SKU únicos",
                lstskus.size()
        );
		
        String sql = """
                SELECT
                    ID_SKU_CODIGO,
                    ID_PRODUCTO
                FROM INV_C_OBJETOS
                WHERE ID_SKU_CODIGO IN ( :skus )""" ;
        
        Map< String, Object > parametrosIn = Map.of( "skus", lstskus ) ;

        return ejecutor.query(
                sql,
                parametrosIn,
                rs -> {
                    Map<String, Integer> resultado = new LinkedHashMap<>();
                    while( rs.next() ){
                        String sku = rs.getString("ID_SKU_CODIGO");
                        Integer idProducto = rs.getInt("ID_PRODUCTO");

                        if ( resultado.containsKey( sku ) ){
                            throw new DatoInvalidoExcepcion(
                                    "El SKU " + sku
                                    + " tiene más de un producto en catálogo"
                            );
                        }
                        resultado.put( sku, idProducto );
                    }
                    return resultado;
                }
        );
	}
}