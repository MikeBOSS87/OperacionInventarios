package com.ope.inv.dominio.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetalleExistencia implements Serializable {

	/**
	 * 
	 */
   private static final long serialVersionUID = -5808974323605733728L ;
   
   private Integer Id_Un_Medida ;
   private String Id_SKU_Codigo ;
   private Integer Id_Obj_Alm ;
   private LocalDateTime FechaDoc ;
   private String Folio ;
   private Integer Id_Det ;
   private Integer Tipo ;
   private BigDecimal Cantidad ;
   
   /**
    * @return the id_Un_Medida
    */
   public Integer getId_Un_Medida() {
	return Id_Un_Medida;
   }
   /**
    * @param id_Un_Medida the id_Un_Medida to set
    */
   public void setId_Un_Medida(Integer id_Un_Medida) {
	Id_Un_Medida = id_Un_Medida;
   }
   
   /**
    * @return the id_SKU_Codigo
    */
   public String getId_SKU_Codigo() {
	return Id_SKU_Codigo;
   }
   /**
    * @param id_SKU_Codigo the id_SKU_Codigo to set
    */
   public void setId_SKU_Codigo(String id_SKU_Codigo) {
	Id_SKU_Codigo = id_SKU_Codigo;
   }
   
   /**
    * @return the id_Obj_Alm
    */
   public Integer getId_Obj_Alm() {
	return Id_Obj_Alm;
   }
   /**
    * @param id_Obj_Alm the id_Obj_Alm to set
    */
   public void setId_Obj_Alm(Integer id_Obj_Alm) {
	Id_Obj_Alm = id_Obj_Alm;
   }
   
   /**
    * @return the fechaDoc
    */
   public LocalDateTime getFechaDoc() {
	return FechaDoc;
   }
   /**
    * @param fechaDoc the fechaDoc to set
    */
   public void setFechaDoc(LocalDateTime fechaDoc) {
	FechaDoc = fechaDoc;
   }
   
   /**
    * @return the folio
    */
   public String getFolio() {
	return Folio;
   }
   /**
    * @param folio the folio to set
    */
   public void setFolio(String folio) {
	Folio = folio;
   }
   
   /**
    * @return the id_Det
    */
   public Integer getId_Det() {
	return Id_Det;
   }
   /**
    * @param id_Det the id_Det to set
    */
   public void setId_Det(Integer id_Det) {
	Id_Det = id_Det;
   }
   
   /**
    * @return the tipo
    */
   public Integer getTipo() {
	return Tipo;
   }
   /**
    * @param tipo the tipo to set
    */
   public void setTipo(Integer tipo) {
	Tipo = tipo;
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
   public void setCantidad(BigDecimal cantidad) {
	Cantidad = cantidad;
   }
}