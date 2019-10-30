package com.prueba.tecnica.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.api.constants.PathServicios;

@RestController
@RequestMapping(PathServicios.PRUEBA)
public class PruebaController {

   @GetMapping("mensaje")
   public String mensajePrueba(@RequestParam(value = "valor") String valor) {

      return "Respuesta servicio= " + valor + "!!";
   }

}
