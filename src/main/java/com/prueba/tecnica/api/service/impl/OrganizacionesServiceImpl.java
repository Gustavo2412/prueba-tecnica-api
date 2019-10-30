package com.prueba.tecnica.api.service.impl;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.prueba.tecnica.api.controller.dto.HistoricoOrganizacionDTO;
import com.prueba.tecnica.api.controller.dto.OrganizacionDTO;
import com.prueba.tecnica.api.controller.dto.OrganizacionResponseDTO;
import com.prueba.tecnica.api.controller.model.HistoricoOrganizacion;
import com.prueba.tecnica.api.controller.model.Organizacion;
import com.prueba.tecnica.api.service.OrganizacionesService;
import com.prueba.tecnica.api.service.repository.OrganizacionesRepositiry;

@Service
public class OrganizacionesServiceImpl implements OrganizacionesService {

   @Autowired
   private OrganizacionesRepositiry organizacionesRepositiry;

   private static Integer           AUTOINCREMENTAL = new Integer(1);

   @Override
   public OrganizacionResponseDTO guardar(OrganizacionDTO organizacionRequestDTO) {

      // Mapeo de Dto a Model
      Organizacion organizacion = new DozerBeanMapper().map(organizacionRequestDTO, Organizacion.class);

      // Se genera idExterno
      String idExterno = organizacionRequestDTO.getNombre().substring(0, 4)
            + organizacionRequestDTO.getTelefono().substring(organizacionRequestDTO.getTelefono().length() - 4, organizacionRequestDTO.getTelefono().length())
            + "NE" + AUTOINCREMENTAL++;

      // Encripto el id externo
      organizacion.setIdExterno(encriptar(idExterno, organizacionRequestDTO.getCodigo()));

      // Guardo la organizacion, se gusrdan en un mapara estatico
      organizacion = this.organizacionesRepositiry.guardar(idExterno, organizacion);

      OrganizacionResponseDTO organizacionResponseDTO = new DozerBeanMapper().map(organizacion, OrganizacionResponseDTO.class);

      // System.out.println("Desencriptado:
      // "+desencriptar(organizacion.getIdExterno(),organizacionRequestDTO.getCodigo()));

      organizacionResponseDTO.setIdExterno(desencriptar(organizacion.getIdExterno(), organizacionRequestDTO.getCodigo()));

      HistoricoOrganizacion historicoOrganizacion = new DozerBeanMapper().map(organizacionResponseDTO, HistoricoOrganizacion.class);
      historicoOrganizacion.setAccion("ALta");
      this.organizacionesRepositiry.guardarHistorico(idExterno, historicoOrganizacion);

      return organizacionResponseDTO;

      // String json= convertirJson(organizacion);
      //
      // System.out.println("Json Organizacion: "+json);
      // System.out.println("Json a Organizacion:
      // "+convertirOrganizacion(json));
      //
      //
      //
      //
      // String codigoEncriptacion = "codigoEncriptacion";
      // String texto="Hola mensaje encrptado";
      //
      // String textEncliptado = encriptar(texto,codigoEncriptacion);
      //
      // System.out.println("MEnsaje Encpitado: "+textEncliptado);
      // System.out.println("MEnsaje Desencriptado:
      // "+desencriptar(textEncliptado,codigoEncriptacion));

   }

   @Override
   public OrganizacionResponseDTO conusltar(String idExterno, String codigo) throws Exception {

      Organizacion organizacion = this.organizacionesRepositiry.consultar(idExterno);

      if (organizacion != null && organizacion.getIdExterno() != null) {

         if (codigo.equals(organizacion.getCodigo())) {
            OrganizacionResponseDTO organizacionResponseDTO = new DozerBeanMapper().map(organizacion, OrganizacionResponseDTO.class);

            organizacionResponseDTO.setIdExterno(desencriptar(organizacion.getIdExterno(), organizacionResponseDTO.getCodigo()));

            return organizacionResponseDTO;
         } else {
            throw new Exception("Codigo incorrecto");
         }

      } else {
         throw new Exception("No exite organizacion");
      }

   }

   @Override
   public void actualizar(String idExterno, OrganizacionDTO organizacionDTO) throws Exception {

      OrganizacionResponseDTO organizacionResponseDTOOriginal = this.conusltar(idExterno, organizacionDTO.getCodigo());

      Organizacion organizacion = new DozerBeanMapper().map(organizacionDTO, Organizacion.class);

      organizacion.setIdExterno(encriptar(idExterno, organizacionResponseDTOOriginal.getCodigo()));

      Organizacion organizacionUpdate = this.organizacionesRepositiry.actualizar(idExterno, organizacion);

      HistoricoOrganizacion historicoOrganizacion = new DozerBeanMapper().map(organizacionUpdate, HistoricoOrganizacion.class);
      historicoOrganizacion.setAccion("Actualizado");
      this.organizacionesRepositiry.guardarHistorico(idExterno, historicoOrganizacion);

   }

   @Override
   public void eliminar(String idExterno, String codigo) throws Exception {
      // TODO Auto-generated method stub

      OrganizacionResponseDTO organizacionResponseDTOOriginal = this.conusltar(idExterno, codigo);

      this.organizacionesRepositiry.elimminar(idExterno);

      HistoricoOrganizacion historicoOrganizacion = new DozerBeanMapper().map(organizacionResponseDTOOriginal, HistoricoOrganizacion.class);
      historicoOrganizacion.setAccion("Eliminado");
      this.organizacionesRepositiry.guardarHistorico(idExterno, historicoOrganizacion);

   }

   @Override
   public List<HistoricoOrganizacionDTO> consultarHistorico(String idExterno, String codigo) throws Exception {

      List<HistoricoOrganizacion> lt = this.organizacionesRepositiry.consultarHistorico(idExterno);

      List<HistoricoOrganizacionDTO> ltDto = new ArrayList<HistoricoOrganizacionDTO>();

      if (!CollectionUtils.isEmpty(lt)) {
         lt.forEach(model -> {

            if (model.getCodigo().equals(codigo)) {
               ltDto.add(new DozerBeanMapper().map(model, HistoricoOrganizacionDTO.class));
            }

         });
      }

      return ltDto;
   }

   // private String convertirJson(Organizacion organizacion)
   // {
   // String json=null;
   // ObjectMapper objectMapper = new ObjectMapper();
   //
   // try
   // {
   // json = objectMapper.writeValueAsString(organizacion);
   // }
   // catch (JsonProcessingException e)
   // {
   //
   // System.err.println(e.getMessage());
   // }
   //
   // return json;
   // }
   //
   //
   //
   //
   // private Organizacion convertirOrganizacion(String json)
   // {
   // Organizacion organizacion=null;
   // ObjectMapper objectMapper = new ObjectMapper();
   //
   // try
   // {
   // organizacion = objectMapper.readValue(json, Organizacion.class);
   // }
   // catch (JsonProcessingException e)
   // {
   //
   // System.err.println(e.getMessage());
   // }
   //
   // return organizacion;
   // }

   private String encriptar(String texto, String codigoEncriptacion) {

      String textoEncriptado = "";

      try {

         MessageDigest messageDigest = MessageDigest.getInstance("MD5");
         byte[] codEncriptacion = messageDigest.digest(codigoEncriptacion.getBytes("utf-8"));

         SecretKey key = new SecretKeySpec(Arrays.copyOf(codEncriptacion, 24), "DESede");
         Cipher cipher = Cipher.getInstance("DESede");
         cipher.init(Cipher.ENCRYPT_MODE, key);

         byte[] base64 = Base64.encodeBase64(cipher.doFinal(texto.getBytes("utf-8")));

         textoEncriptado = new String(base64);

      } catch (Exception ex) {
         System.out.println(ex);
      }

      return textoEncriptado;
   }

   private String desencriptar(String textoEncriptado, String codigoEncriptacion) {

      String texto = "";

      try {
         byte[] mensaje = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));

         MessageDigest messageDigest = MessageDigest.getInstance("MD5");

         byte[] codEncriptacion = messageDigest.digest(codigoEncriptacion.getBytes("utf-8"));

         SecretKey key = new SecretKeySpec(Arrays.copyOf(codEncriptacion, 24), "DESede");

         Cipher decipher = Cipher.getInstance("DESede");
         decipher.init(Cipher.DECRYPT_MODE, key);

         texto = new String(decipher.doFinal(mensaje), "UTF-8");

      } catch (Exception ex) {
         System.out.println(ex);
      }
      return texto;
   }

   public OrganizacionesRepositiry getOrganizacionesRepositiry() {
      return organizacionesRepositiry;
   }

   public void setOrganizacionesRepositiry(OrganizacionesRepositiry organizacionesRepositiry) {
      this.organizacionesRepositiry = organizacionesRepositiry;
   }

}
