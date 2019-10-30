package com.prueba.tecnica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.api.constants.PathServicios;
import com.prueba.tecnica.api.controller.dto.HistoricoOrganizacionDTO;
import com.prueba.tecnica.api.controller.dto.OrganizacionDTO;
import com.prueba.tecnica.api.controller.dto.OrganizacionResponseDTO;
import com.prueba.tecnica.api.service.OrganizacionesService;

@RestController
@RequestMapping(PathServicios.ORGANIZACION)
public class OrganizacionesController {

   @Autowired
   private OrganizacionesService organizacionesService;

   @PostMapping("alta")
   public ResponseEntity<OrganizacionResponseDTO> alta(@Valid @RequestBody OrganizacionDTO organizacionRequestDTO) {

      // try
      // {
      return ResponseEntity.ok().body(organizacionesService.guardar(organizacionRequestDTO));
      // } catch (Exception e) {
      // return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new
      // UsuarioTokenDTO(e.getMessage()));
      // }

   }

   @GetMapping("consulta")
   public ResponseEntity<OrganizacionResponseDTO> consulta(@RequestParam(name = "idExterno", required = true) String idExterno,
         @RequestParam(name = "codigo", required = true) String codigo) {

      try {
         return ResponseEntity.ok().body(organizacionesService.conusltar(idExterno, codigo));
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new OrganizacionResponseDTO(e.getMessage()));
      }

   }

   @PutMapping("actualizar")
   public ResponseEntity<String> actualizar(@RequestParam(name = "idExterno", required = true) String idExterno,
         @Valid @RequestBody OrganizacionDTO organizacionRequestDTO) {

      try {
         organizacionesService.actualizar(idExterno, organizacionRequestDTO);

         return ResponseEntity.ok().build();
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
      }

   }

   @DeleteMapping("eliminar")
   public ResponseEntity<String> eliminar(@RequestParam(name = "idExterno", required = true) String idExterno,
         @RequestParam(name = "codigo", required = true) String codigo) {

      try {
         organizacionesService.eliminar(idExterno, codigo);

         return ResponseEntity.ok().build();
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
      }

   }

   @GetMapping("consulta-historico")
   public ResponseEntity<List<HistoricoOrganizacionDTO>> consultaHistorico(@RequestParam(name = "idExterno", required = true) String idExterno,
         @RequestParam(name = "codigo", required = true) String codigo) {

      try {
         return ResponseEntity.ok().body(organizacionesService.consultarHistorico(idExterno, codigo));
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();

         return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
      }

   }

   public OrganizacionesService getOrganizacionesService() {
      return organizacionesService;
   }

   public void setOrganizacionesService(OrganizacionesService organizacionesService) {
      this.organizacionesService = organizacionesService;
   }

}
