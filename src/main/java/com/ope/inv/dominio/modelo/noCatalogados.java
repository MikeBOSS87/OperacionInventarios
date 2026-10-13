package com.ope.inv.dominio.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class noCatalogados implements Serializable {

	private static final long serialVersionUID = -5766654786832590730L;
	
	private String Id_SKU_Codigo ;
	private BigDecimal Cantidad ;
	
	public noCatalogados( String id_SKU_Codigo, BigDecimal cantidad ) {
		super();
		Id_SKU_Codigo = id_SKU_Codigo;
		Cantidad = cantidad;
	}
	
	/**
	 * @return the id_SKU_Codigo
	 */
	public String getId_SKU_Codigo(){
		return Id_SKU_Codigo;
	}
	/**
	 * @param id_SKU_Codigo the id_SKU_Codigo to set
	 */
	public void setId_SKU_Codigo( String id_SKU_Codigo ) {
		Id_SKU_Codigo = id_SKU_Codigo;
	}
	
	/**
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return Cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad( BigDecimal cantidad ) {
		Cantidad = cantidad;
	}	
}