/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.dto.ServicioTipoDTO;
import org.una.aerolinea.entities.ServicioTipo;
import org.una.aerolinea.services.IServicioTipoService;

/**
 *
 * @author Luis
 */
@RestController
@RequestMapping("/tipos_servicios_aeropuerto") 
@Api(tags = {"Tipos_Servicios_Aeropuerto"})
public class ServicioTipoController {
      
@Autowired
private IServicioTipoService tipoServiAeroService;

   @GetMapping("/") 
   @ApiOperation(value = "Obtiene una lista de todos los tipos de servicios del aeropuerto", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
   public @ResponseBody
   ResponseEntity<?> findAll() {
       try {
           Optional<List<ServicioTipo>> resultadoFound = tipoServiAeroService.findAll();
           if (resultadoFound.isPresent()) {
               List<ServicioTipoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioTipoDTO.class);
               return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
       } catch (Exception e) {
           return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un tipo de servicio del aeropuerto por su id", response = ServicioTipoDTO.class, tags = "Tipos_Servicios_Aeropuerto")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ServicioTipo> resultadoFound = tipoServiAeroService.findById(id);
            if (resultadoFound.isPresent()) {
                ServicioTipoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), ServicioTipoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los tipos de servicios del aeropuerto por estado", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<ServicioTipo>> resultadoFound = tipoServiAeroService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<ServicioTipoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioTipoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
        @GetMapping("/list/descripcion/{descripcion}") 
    @ApiOperation(value = "Obtiene una lista de tipos de servicios del aeropuerto por su descripcion", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
    public ResponseEntity<?> findByDistanciaRango(@PathVariable(value = "descripcion") String descripcion) {
        try {
            Optional<List<ServicioTipo>> resultadoFound = tipoServiAeroService.findByDescripcion(descripcion);
            if (resultadoFound.isPresent()) {
                List<ServicioTipoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioTipoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de tipos de aviones por su nombre", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ServicioTipo>> resultadoFound = tipoServiAeroService.findByNombreContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<ServicioTipoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioTipoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
       
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un tipo de servicio del aeropuerto", response = ServicioTipoDTO.class, tags = "Tipos_Servicios_Aeropuerto")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ServicioTipo tipoServicioAeropuerto) {
        try {
            ServicioTipo entityCreated = tipoServiAeroService.create(tipoServicioAeropuerto);
            ServicioTipoDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, ServicioTipoDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un tipo de servicio del aeropuerto", response = ServicioTipoDTO.class, tags = "Tipos_Servicios_Aeropuerto")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServicioTipo entityModified) {
        try {
            Optional<ServicioTipo> entityUpdated = tipoServiAeroService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                ServicioTipoDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), ServicioTipoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
