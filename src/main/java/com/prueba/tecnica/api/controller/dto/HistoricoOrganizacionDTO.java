package com.prueba.tecnica.api.controller.dto;

import java.io.Serializable;

public class HistoricoOrganizacionDTO implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String            accion;

   private String            nombre;
   private String            direccion;
   private String            telefono;
   private String            codigo;
   private String            idExterno;

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getDireccion() {
      return direccion;
   }

   public void setDireccion(String direccion) {
      this.direccion = direccion;
   }

   public String getTelefono() {
      return telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public String getCodigo() {
      return codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public String getIdExterno() {
      return idExterno;
   }

   public void setIdExterno(String idExterno) {
      this.idExterno = idExterno;
   }

   public String getAccion() {
      return accion;
   }

   public void setAccion(String accion) {
      this.accion = accion;
   }

}
