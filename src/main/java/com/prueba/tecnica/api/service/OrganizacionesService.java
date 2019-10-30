package com.prueba.tecnica.api.service;

import java.util.List;

import com.prueba.tecnica.api.controller.dto.HistoricoOrganizacionDTO;
import com.prueba.tecnica.api.controller.dto.OrganizacionDTO;
import com.prueba.tecnica.api.controller.dto.OrganizacionResponseDTO;

public interface OrganizacionesService {

   OrganizacionResponseDTO guardar(OrganizacionDTO organizacionRequestDTO);

   OrganizacionResponseDTO conusltar(String idExterno, String codigo) throws Exception;

   void actualizar(String idExterno, OrganizacionDTO organizacionDTO) throws Exception;

   void eliminar(String idExterno, String codigo) throws Exception;

   List<HistoricoOrganizacionDTO> consultarHistorico(String idExterno, String codigo) throws Exception;

}
