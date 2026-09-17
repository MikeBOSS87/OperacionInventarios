package com.ope.inv.infra.ent.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalleProductoEntradaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4834020001503746271L;
	
	@JsonProperty( "Id_Uni_Medida" )
	private Integer idUnidadMedida;
	
	private String SKU;
	
	@JsonProperty( "Id_Producto" )
    private Integer idProducto;
    
    private String Tipo;
    
    private Integer Cantidades;
    
	/**
	 * @return the idUnidadMedida
	 */
	public Integer getIdUnidadMedida() {
		return idUnidadMedida;
	}
	/**
	 * @param idUnidadMedida the idUnidadMedida to set
	 */
	public void setIdUnidadMedida( Integer idUnidadMedida ) {
		this.idUnidadMedida = idUnidadMedida;
	}
	
	/**
	 * @return the sKU
	 */
	public String getSKU() {
		return SKU;
	}
	/**
	 * @param sKU the sKU to set
	 */
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	
	/**
	 * @return the id_producto
	 */
	public Integer getIdProducto() {
		return idProducto;
	}
	/**
	 * @param id_producto the id_producto to set
	 */
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto ;
	}
	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return Tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	/**
	 * @return the cantidades
	 */
	public Integer getCantidades() {
		return Cantidades;
	}
	/**
	 * @param cantidades the cantidades to set
	 */
	public void setCantidades(Integer cantidades) {
		Cantidades = cantidades;
	}
}