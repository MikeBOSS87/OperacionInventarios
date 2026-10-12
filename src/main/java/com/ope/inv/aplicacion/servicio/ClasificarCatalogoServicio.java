package com.ope.inv.aplicacion.servicio;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import com.ope.inv.dominio.modelo.DetalleExistencia;
import com.ope.inv.dominio.modelo.Existencia;
import com.ope.inv.dominio.modelo.noCatalogados;
import com.ope.inv.dominio.puertos.sal.ConsultaProductoRepositorio;

public class ClasificarCatalogoServicio {
	
	private final ConsultaProductoRepositorio consultaProductoRepositorio;

    public ClasificarCatalogoServicio(
            ConsultaProductoRepositorio consultaProductoRepositorio) {

        this.consultaProductoRepositorio = consultaProductoRepositorio;
    }
    
    public Existencia clasificar( Existencia existencia ) {

        List<String> skus = existencia.getDetalleDoc()
                .stream()
                .map( DetalleExistencia::getId_SKU_Codigo )
                .distinct()
                .toList();

        Map<String, Integer> productosPorSku =
                consultaProductoRepositorio.consultarPorSku( skus );

        List<DetalleExistencia> detallesCatalogados = new ArrayList<>();
        List<noCatalogados> objetosNoCatalogados = new ArrayList<>();
        Set<String> skusNoCatalogados = new HashSet<>();

        for( DetalleExistencia detalle : existencia.getDetalleDoc() ) {

            Integer idProducto =
                    productosPorSku.get(detalle.getId_SKU_Codigo());

            if (idProducto != null) {
                detalle.setId_Producto(idProducto);
                detallesCatalogados.add(detalle);
                continue;	
            }

            if (skusNoCatalogados.add(detalle.getId_SKU_Codigo())) {
                objetosNoCatalogados.add(
                        new noCatalogados(
                                detalle.getId_SKU_Codigo(),
                                detalle.getCantidad()
                        )
                );
            }
        }

        existencia.setDetalleDoc( detallesCatalogados );
        existencia.setDetalleNoCatalogado( objetosNoCatalogados );

        return existencia;
    }
}