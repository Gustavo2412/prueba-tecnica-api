package com.prueba.tecnica.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.prueba.tecnica.api.constants.PathServicios;

@Configuration
@EnableWebSecurity
public class ConfiguracionWeb extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws Exception {

      // TODO Auto-generated method stub
      // super.configure(http);

      TokenFiltro filtro = new TokenFiltro();

      http.csrf().disable();
      http.addFilterAfter(filtro, UsernamePasswordAuthenticationFilter.class);
      http.authorizeRequests().antMatchers(HttpMethod.POST, PathServicios.TOKEN + "/" + "crear").permitAll().anyRequest().authenticated();
   }

}
