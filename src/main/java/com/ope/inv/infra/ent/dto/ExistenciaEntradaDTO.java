package com.ope.inv.infra.ent.dto;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExistenciaEntradaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1193457028845825846L;
	
	private String fecha;
	
	@JsonProperty("Id_Persona")
    private String idPersona;
	
    private String suc;
    
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