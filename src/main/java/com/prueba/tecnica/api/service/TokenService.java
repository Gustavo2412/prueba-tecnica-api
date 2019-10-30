package com.prueba.tecnica.api.service;

import com.prueba.tecnica.api.controller.dto.UsuarioTokenDTO;

public interface TokenService {

   UsuarioTokenDTO crearToken(String usuario, String contrasena) throws Exception;

}
