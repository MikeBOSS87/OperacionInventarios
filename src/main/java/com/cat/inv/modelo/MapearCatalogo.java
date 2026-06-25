package com.cat.inv.modelo;

import java.util.Map;

public class MapearCatalogo {
	
	public static Catalogo map( Map<String, Object> row ){
		Catalogo item = new Catalogo();

        item.setIdTipo( Integer.valueOf( row.get("ID_TIPO_CAT").toString() ) );
        item.setTipoDescripcion((String) row.get("TIPO_DESCRIPCION"));
        item.setSkuCodigo((String) row.get("SKU_CODIGO"));
        item.setDescripcionSku((String) row.get("DESCRIPCION_SKU"));
        item.setCostoUnitario( row.get("COSTO_UNITARIO") != null ? Double.valueOf( row.get("COSTO_UNITARIO").toString() ) : 0.0 );
        item.setId( Integer.valueOf( row.get("ID").toString() ) );
        item.setFechaMod((String) row.get("FECHA_MOD"));

        return item;
    }
}