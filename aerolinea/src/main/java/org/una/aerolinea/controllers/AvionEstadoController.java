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
import org.una.aerolinea.services.IAvionEstadoService;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.dto.AvionEstadoDTO;
import org.una.aerolinea.entities.AvionEstado;
/**
 *
 * @author Luis
 */

@RestController
@RequestMapping("/aviones_estados") 
@Api(tags = {"Aviones_Estados"})
public class AvionEstadoController {
    
@Autowired
private IAvionEstadoService avionEstadoService;
    
   @GetMapping("/") 
   @ApiOperation(value = "Obtiene una lista de todos estados de los aviones", response = AvionEstadoDTO.class, responseContainer = "List", tags = "Aviones_Estados")
   public @ResponseBody
   ResponseEntity<?> findAll() {
       try {
           Optional<List<AvionEstado>> resultadoFound = avionEstadoService.findAll();
           if (resultadoFound.isPresent()) {
               List<AvionEstadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionEstadoDTO.class);
               return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
       } catch (Exception e) {
           return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }   
   
   
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un estado de avion por su id", response = AvionEstadoDTO.class, tags = "Aviones_Estados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<AvionEstado> resultadoFound = avionEstadoService.findById(id);
            if (resultadoFound.isPresent()) {
                AvionEstadoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), AvionEstadoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/combustible/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de los aviones", response = AvionEstadoDTO.class, responseContainer = "List", tags = "Aviones_Estados")
    public ResponseEntity<?> findByCombustible(@PathVariable(value = "term") int term) {
        try {
            Optional<List<AvionEstado>> resultadoFound = avionEstadoService.findByCombustible(term);
            if (resultadoFound.isPresent()) {
                List<AvionEstadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionEstadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/distancia/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de aviones por su distancia recorrida", response = AvionEstadoDTO.class, responseContainer = "List", tags = "Aviones_Estados")
    public ResponseEntity<?> findByDistanciaRecorrida(@PathVariable(value = "term") int term) {
        try {
            Optional<List<AvionEstado>> resultadoFound = avionEstadoService.findByDistanciaRecorrida(term);
            if (resultadoFound.isPresent()) {
                List<AvionEstadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionEstadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de los aviones por estado", response = AvionEstadoDTO.class, responseContainer = "List", tags = "Aviones_Estados")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<AvionEstado>> resultadoFound = avionEstadoService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<AvionEstadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionEstadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/tiempo/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de aviones por su tiempo en tierra", response = AvionEstadoDTO.class, responseContainer = "List", tags = "Aviones_Estados")
    public ResponseEntity<?> findByTiempoTierra(@PathVariable(value = "term") int term) {
        try {
            Optional<List<AvionEstado>> resultadoFound = avionEstadoService.findByTiempoTierra(term);
            if (resultadoFound.isPresent()) {
                List<AvionEstadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionEstadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/ubicacion/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de aviones por su ubicacion", response = AvionEstadoDTO.class, responseContainer = "List", tags = "Aviones_Estados")
    public ResponseEntity<?> findByUbicacion(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AvionEstado>> resultadoFound = avionEstadoService.findByUbicacionContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<AvionEstadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionEstadoDTO.class);
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
    @ApiOperation(value = "Crea un tipo de servicio del aeropuerto", response = AvionEstadoDTO.class, tags = "Aviones_Estados")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody AvionEstado tipoServicioAeropuerto) {
        try {
            AvionEstado entityCreated = avionEstadoService.create(tipoServicioAeropuerto);
            AvionEstadoDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, AvionEstadoDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un tipo de servicio del aeropuerto", response = AvionEstadoDTO.class, tags = "Aviones_Estados")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AvionEstado entityModified) {
        try {
            Optional<AvionEstado> entityUpdated = avionEstadoService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                AvionEstadoDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), AvionEstadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
