package com.ope.inv.infra.persistencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.puertos.sal.ExistenciaRepositorio;

@Repository
public class ExistenciaRepositorioBD implements ExistenciaRepositorio {

	private static final Logger loger = LoggerFactory.getLogger(
			ExistenciaRepositorioBD.class
        );
	
	private final JdbcTemplate ejecutor;

    public ExistenciaRepositorioBD( JdbcTemplate TemplateJDBC ) {
        this.ejecutor = TemplateJDBC;
    }
	
	@Override
	public void Guardar( Existencia existencia ) {
		String sql = """
                INSERT INTO INV_O_EXISTENCIAS (
                    ID_EMPLEADO,
                    ID_SUCURSAL,
                    FOLIO,
                    FECHA_DOC
                )
                VALUES (?, ?, ?, ?)
                """;

        ejecutor.update(
                sql,
                existencia.getId_Empleado(),
                existencia.getId_Sucursal(),
                existencia.getFolio(),
                existencia.getFecha_Doc()
        );
        loger.debug(
		        "Se creo el documento en la tabla de existencias"
			    );
        
        String sqlDetalle = """
                INSERT INTO INV_O_EXIS_DET (
                    ID_UN_MED,
                    ID_SKU_CODIGO,
                    ID_PRODUCTO,
                    FECHA_DOC,
                    FOLIO,
                    ID_DET,
                    TIPO,
                    CANTIDAD
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        for( DetalleExistencia detalle : existencia.getDetalleDoc() ) {
            ejecutor.update(
                    sqlDetalle,
                    detalle.getId_Un_Medida(),
                    detalle.getId_SKU_Codigo(),
                    detalle.getId_Producto(),
                    detalle.getFechaDoc(),
                    detalle.getFolio(),
                    detalle.getId_Det(),
                    detalle.getTipo(),
                    detalle.getCantidad()
            ) ;
        }
        
        loger.debug(
		        "Se guardo el documento y su detalle."
			    );
	}
}