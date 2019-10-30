package com.prueba.tecnica.api.service.repository;

import java.util.List;

import com.prueba.tecnica.api.controller.model.HistoricoOrganizacion;
import com.prueba.tecnica.api.controller.model.Organizacion;

public interface OrganizacionesRepositiry {

   Organizacion guardar(String idExterno, Organizacion organizacion);

   Organizacion consultar(String idExterno);

   Organizacion actualizar(String idExterno, Organizacion organizacion);

   void elimminar(String idExterno);

   void guardarHistorico(String idExterno, HistoricoOrganizacion historicoOrganizacion);

   List<HistoricoOrganizacion> consultarHistorico(String idExterno);

}
