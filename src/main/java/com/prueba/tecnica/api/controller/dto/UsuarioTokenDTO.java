package com.prueba.tecnica.api.controller.dto;

public class UsuarioTokenDTO {

   private String usuario;
   private String token;
   private String mensaje;

   public UsuarioTokenDTO(String mensaje) {
      super();
      this.mensaje = mensaje;
   }

   public UsuarioTokenDTO(String usuario, String token) {
      super();
      this.usuario = usuario;
      this.token = token;
   }

   public String getUsuario() {
      return usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getMensaje() {
      return mensaje;
   }

   public void setMensaje(String mensaje) {
      this.mensaje = mensaje;
   }

}
