package com.cat.inv.modelo;

import java.io.Serializable;

public class Catalogo implements Serializable{
   
   private Integer idTipo;
   private String tipoDescripcion;
   private String skuCodigo;
   private String descripcionSku;
   private Double costoUnitario;
   private Integer id;
   private String fechaMod;
   private static final long serialVersionUID = 6486869907006003323L;
   
   /**
    * @return the idTipo
    */
   public Integer getIdTipo() {
      return idTipo;
   }
   /**
    * @param idTipo the idTipo to set
    */
   public void setIdTipo(Integer idTipo) {
      this.idTipo = idTipo;
   }
   /**
    * @return the tipoDescripcion
    */
   public String getTipoDescripcion() {
	  return tipoDescripcion;
   }
   /**
    * @param tipoDescripcion the tipoDescripcion to set
    */
   public void setTipoDescripcion(String tipoDescripcion) {
	  this.tipoDescripcion = tipoDescripcion;
   }
   /**
    * @return the skuCodigo
    */
   public String getSkuCodigo() {
	return skuCodigo;
   }
   /**
    * @param skuCodigo the skuCodigo to set
    */
   public void setSkuCodigo(String skuCodigo) {
	this.skuCodigo = skuCodigo;
   }
   /**
    * @return the descripcionSku
    */
   public String getDescripcionSku() {
	return descripcionSku;
   }
   /**
    * @param descripcionSku the descripcionSku to set
    */
   public void setDescripcionSku(String descripcionSku) {
	this.descripcionSku = descripcionSku;
   }
   /**
    * @return the costoUnitario
    */
   public Double getCostoUnitario() {
	return costoUnitario;
   }
   /**
    * @param costoUnitario the costoUnitario to set
    */
   public void setCostoUnitario(Double costoUnitario) {
	this.costoUnitario = costoUnitario;
   }
   /**
    * @return the id
    */
   public Integer getId() {
	return id;
   }
   /**
    * @param id the id to set
    */
   public void setId(Integer id) {
	this.id = id;
   }
   /**
    * @return the fechaMod
    */
   public String getFechaMod() {
	return fechaMod;
   }
   /**
    * @param fechaMod the fechaMod to set
    */
   public void setFechaMod(String fechaMod) {
	this.fechaMod = fechaMod;
   }
}