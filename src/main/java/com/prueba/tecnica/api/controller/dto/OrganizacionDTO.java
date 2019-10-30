package com.prueba.tecnica.api.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class OrganizacionDTO implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String            nombre;

   private String            direccion;

   @Size(min = 10, max = 10, message = "El parámetro telefono es incorrecto, debe de ser de 10 posiciones")

   private String            telefono;

   @Size(min = 8, max = 8, message = "El parámetro codigo es incorrecto, debe de ser de 8 posiciones")

   private String            codigo;

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

}
