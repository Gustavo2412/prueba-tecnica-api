package com.prueba.tecnica.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.api.constants.PathServicios;
import com.prueba.tecnica.api.controller.dto.UsuarioTokenDTO;
import com.prueba.tecnica.api.service.TokenService;

@RestController
@RequestMapping(PathServicios.TOKEN)
public class TokenController {

   @Autowired
   private TokenService tokenService;

   @PostMapping("crear")
   public ResponseEntity<UsuarioTokenDTO> login(@RequestParam(name = "usuario", required = true, defaultValue = "") String usuario,
         @RequestParam(name = "contrasena", required = true, defaultValue = "") String contrasena) {

      try {
         return ResponseEntity.ok().body(tokenService.crearToken(usuario, contrasena));
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new UsuarioTokenDTO(e.getMessage()));
      }

   }

   public TokenService getTokenService() {
      return tokenService;
   }

   public void setTokenService(TokenService tokenService) {
      this.tokenService = tokenService;
   }

}
