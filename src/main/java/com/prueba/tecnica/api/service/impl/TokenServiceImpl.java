package com.prueba.tecnica.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.api.controller.dto.UsuarioTokenDTO;
import com.prueba.tecnica.api.service.TokenService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

   @Value(value = "${llave-secreta-token}")
   private String llaveToken;

   @Value(value = "${id-token}")
   private String idToken;

   @Value(value = "${usuarios}")
   private String usuarios;

   @Value(value = "${contrasenas}")
   private String contrasenas;

   @Override
   public UsuarioTokenDTO crearToken(String usuario, String contrasena) throws Exception {

      String[] ltUsuarios = usuarios.split(",");
      String[] ltContrasenas = contrasenas.split(",");

      Boolean existeUsuario = Boolean.FALSE;

      for (int index = 0; index < ltUsuarios.length; index++) {
         if (ltUsuarios[index].equals(usuario)) {
            if (ltContrasenas[index].equals(contrasena))
               existeUsuario = Boolean.TRUE;
         }
      }

      if (existeUsuario) {

         System.out.println("propertie-llaveToken= " + llaveToken);

         System.out.println("propertie-idToken= " + idToken);

         List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

         Date date = new Date(System.currentTimeMillis());
         Date expira = new Date(System.currentTimeMillis() + 30000000);

         String token = Jwts.builder().setId(idToken).setSubject(usuario)
               .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).setIssuedAt(date)
               .setExpiration(expira).signWith(SignatureAlgorithm.HS512, llaveToken.getBytes()).compact();

         System.out.println("Token-Generado= " + token);

         return new UsuarioTokenDTO(usuario, "Bearer" + token);

      } else {
         throw new Exception("Usuario o contraseña incorrecta");
      }

   }

}
