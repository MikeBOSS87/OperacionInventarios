package com.ope.inv.infra.ent.dto;

import java.util.List;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExistenciaEntradaDTO implements Serializable{
	
	private static final long serialVersionUID = 1193457028845825846L;
	
	@Schema(description = "Fecha y hora del documento con formato dd-MM-yyyy HH:mm:ss", example = "21-09-2026 11:03:09", requiredMode = Schema.RequiredMode.REQUIRED)
	private String fecha;
	
	@Schema(description = "Identificador de la persona que registra el movimiento", example = "060987", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonProperty("Id_Persona")
    private String idPersona;
	
    @Schema(description = "Código de sucursal; los primeros ocho caracteres identifican la sucursal", example = "25010609-matriz", requiredMode = Schema.RequiredMode.REQUIRED)
    private String suc;
    
    @Schema(description = "Detalles de productos que se recibirán para su procesamiento", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<DetalleProductoEntradaDTO> detalleProductos;
    
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return the idPersona
	 */
	public String getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	
	/**
	 * @return the suc
	 */
	public String getSuc() {
		return suc;
	}
	/**
	 * @param suc the suc to set
	 */
	public void setSuc(String suc) {
		this.suc = suc;
	}
	
	/**
	 * @return the detalleProductos
	 */
	public List<DetalleProductoEntradaDTO> getDetalleProductos() {
		return detalleProductos;
	}
	/**
	 * @param detalleProductos the detalleProductos to set
	 */
	public void setDetalleProductos(List<DetalleProductoEntradaDTO> detalleProductos) {
		this.detalleProductos = detalleProductos;
	}
}