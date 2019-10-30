package com.prueba.tecnica.api.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenFiltro extends OncePerRequestFilter {

   // @Value(value="${llave-secreta-token}")
   private String llaveToken = "llavePruebaTecnica";

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String header = "Authorization";

      String autentica = request.getHeader(header);

      if (autentica != null && autentica.contains("Bearer")) {
         System.out.println("Token-doFilterInternal= " + request.getHeader(header));

         String jwtToken = request.getHeader(header).replace("Bearer", "");
         Claims claims = Jwts.parser().setSigningKey(llaveToken.getBytes()).parseClaimsJws(jwtToken).getBody();

         if (claims.get("authorities") != null) {

            List<String> authorities = (List<String>) claims.get("authorities");

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                  authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

            SecurityContextHolder.getContext().setAuthentication(auth);

         } else {
            SecurityContextHolder.clearContext();
         }
      }

      filterChain.doFilter(request, response);

   }

}
