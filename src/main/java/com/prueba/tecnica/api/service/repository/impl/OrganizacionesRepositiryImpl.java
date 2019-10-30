package com.prueba.tecnica.api.service.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.prueba.tecnica.api.controller.model.HistoricoOrganizacion;
import com.prueba.tecnica.api.controller.model.Organizacion;
import com.prueba.tecnica.api.service.repository.OrganizacionesRepositiry;

@Repository
public class OrganizacionesRepositiryImpl implements OrganizacionesRepositiry {
   // Has para guardar las organizaciones
   private static HashMap<String, Organizacion>                ORGANIZACIONES           = new HashMap<String, Organizacion>();

   private static HashMap<String, List<HistoricoOrganizacion>> ORGANIZACIONES_HISTORICO = new HashMap<String, List<HistoricoOrganizacion>>();

   @Override
   public Organizacion guardar(String idExterno, Organizacion organizacion) {

      System.out.println("Entro a guardar en HasMap");
      ORGANIZACIONES.put(idExterno, organizacion);

      System.out.println("HasMap: " + ORGANIZACIONES);

      return ORGANIZACIONES.get(idExterno);
   }

   @Override
   public Organizacion consultar(String idExterno) {
      // TODO Auto-generated method stub
      return ORGANIZACIONES.get(idExterno);
   }

   @Override
   public Organizacion actualizar(String idExterno, Organizacion organizacionUpdate) {
      // TODO Auto-generated method stub

      Organizacion organizacion = ORGANIZACIONES.get(idExterno);

      System.out.println("Actualiar...");
      System.out.println("OrganizacionOriginal: " + organizacion);
      System.out.println("OrganizacionUpdate: " + organizacionUpdate);

      ORGANIZACIONES.put(idExterno, organizacionUpdate);

      System.out.println("HasMap Terminar Actualiar: " + ORGANIZACIONES);

      return ORGANIZACIONES.get(idExterno);

   }

   @Override
   public void elimminar(String idExterno) {
      // TODO Auto-generated method stub

      System.out.println("HasMap Antes de Eliminar: " + ORGANIZACIONES);

      ORGANIZACIONES.remove(idExterno);

      System.out.println("HasMap Terminar de Eliminar: " + ORGANIZACIONES);
   }

   @Override
   public void guardarHistorico(String idExterno, HistoricoOrganizacion historicoOrganizacion) {
      // TODO Auto-generated method stub

      if (!ORGANIZACIONES_HISTORICO.containsKey(idExterno)) {
         ORGANIZACIONES_HISTORICO.put(idExterno, new ArrayList<HistoricoOrganizacion>());
      }

      ORGANIZACIONES_HISTORICO.get(idExterno).add(historicoOrganizacion);

   }

   @Override
   public List<HistoricoOrganizacion> consultarHistorico(String idExterno) {
      return ORGANIZACIONES_HISTORICO.get(idExterno);

   }

}
