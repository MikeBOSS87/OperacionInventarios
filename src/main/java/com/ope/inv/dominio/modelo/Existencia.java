package com.ope.inv.dominio.modelo;

import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Existencia implements Serializable{

	/**
	 * 
	 */	
   private static final long serialVersionUID = 7605612050451854434L;
   
   private String Id_Empleado ;
   private Integer Id_Sucursal ;
   private LocalDateTime Fecha_Doc ;
   private String Folio ; 
   private List< DetalleExistencia > DetalleDoc ;
   
   /**
    * @return the id_Empleado
    */
   public String getId_Empleado() {
	return Id_Empleado;
   }
   /**
    * @param id_Empleado the id_Empelado to set
    */
   public void setId_Empleado( String id_Empleado ) {
	   Id_Empleado = id_Empleado;
   }
   
   /**
    * @return the id_Sucursal
    */
   public Integer getId_Sucursal() {
	   return Id_Sucursal;
   }
   /**
    * @param id_Sucursal the id_Sucursal to set
    */
   public void setId_Sucursal(Integer id_Sucursal) {
	Id_Sucursal = id_Sucursal;
   }
   
   /**
    * @return the fecha_Doc
    */
   public LocalDateTime getFecha_Doc() {
	return Fecha_Doc;
   }
   /**
    * @param fecha_Doc the fecha_Doc to set
    */
   public void setFecha_Doc(LocalDateTime fecha_Doc) {
	Fecha_Doc = fecha_Doc;
   }
   
   /**
    * @return the folio
    */
   public String getFolio(){
	   return Folio;
   }
   /**
    * @param folio the folio to set
    */
   public void setFolio(String folio) {
	Folio = folio;
   }
   
   /**
    * @return the detalleDoc
    */
   public List<DetalleExistencia> getDetalleDoc() {
	return DetalleDoc;
   }
   /**
    * @param detalleDoc the detalleDoc to set
    */
   public void setDetalleDoc(List<DetalleExistencia> detalleDoc) {
	DetalleDoc = detalleDoc;
   }  
}