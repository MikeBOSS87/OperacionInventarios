package com.ope.inv.infra.ent.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class DetalleProductoEntradaDTO implements Serializable {
	
	private static final long serialVersionUID = 4834020001503746271L;
	
	@Schema(description = "Identificador de la unidad de medida", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonProperty( "Id_Uni_Medida" )
	private Integer idUnidadMedida;
	
	@Schema(description = "Código SKU del artículo", example = "SKU-5002", requiredMode = Schema.RequiredMode.REQUIRED)
	private String SKU;
    
    @Schema(description = "Tipo de movimiento admitido: Entrada o Salida", example = "Entrada", allowableValues = {"Entrada", "Salida"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private String Tipo;
    
    @Schema(description = "Cantidad del SKU en la unidad de medida indicada", example = "3", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
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
	 * @return the sku
	 */
	public String getSKU() {
		return SKU;
	}
	/**
	 * @param sKU the sKU to set
	 */
	public void setSKU( String sku ) {
		SKU = sku;
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