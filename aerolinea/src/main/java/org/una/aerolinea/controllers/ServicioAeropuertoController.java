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
import org.una.aerolinea.dto.ServicioAeropuertoDTO;
import org.una.aerolinea.entities.ServicioAeropuerto;
import org.una.aerolinea.services.IServicioAeropuertoService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/servicios_aeropuerto") 
@Api(tags = {"Servicios_Aeropuerto"})
public class ServicioAeropuertoController {
    @Autowired
    private IServicioAeropuertoService servicioService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los servicios del aeropuerto", response = ServicioAeropuertoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ServicioAeropuerto>> resultadoFound = servicioService.findAll();
            if (resultadoFound.isPresent()) {
                List<ServicioAeropuertoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioAeropuertoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un servicio del aeropuerto por su id", response = ServicioAeropuertoDTO.class, tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ServicioAeropuerto> resultadoFound = servicioService.findById(id);
            if (resultadoFound.isPresent()) {
                ServicioAeropuertoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), ServicioAeropuertoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los servicios del aeropuerto por estado", response = ServicioAeropuertoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<ServicioAeropuerto>> resultadoFound = servicioService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<ServicioAeropuertoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioAeropuertoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estadoCobro/{term}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por estado de cobro", response = ServicioAeropuertoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByEstadoCobro(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<ServicioAeropuerto>> resultadoFound = servicioService.findByEstadoCobro(term);
            if (resultadoFound.isPresent()) {
                List<ServicioAeropuertoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioAeropuertoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/cobroRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por un rango de cobro", response = ServicioAeropuertoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByCobroRango(@PathVariable(value = "mas") float mas, @PathVariable(value = "menos") float menos) {
        try {
            Optional<List<ServicioAeropuerto>> resultadoFound = servicioService.findByCobroRango(mas, menos);
            if (resultadoFound.isPresent()) {
                List<ServicioAeropuertoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioAeropuertoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/tipo/{term}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por el tipo", response = ServicioAeropuertoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByTipoAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ServicioAeropuerto>> resultadoFound = servicioService.findByTipoContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<ServicioAeropuertoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioAeropuertoDTO.class);
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
    @ApiOperation(value = "Crea un servicio del aeropuerto", response = ServicioAeropuertoDTO.class, tags = "Servicios_Aeropuerto")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ServicioAeropuerto servicio) {
        try {
            ServicioAeropuerto entityCreated = servicioService.create(servicio);
            ServicioAeropuertoDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, ServicioAeropuertoDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un servicio del aeropuerto", response = ServicioAeropuertoDTO.class, tags = "Servicios_Aeropuerto")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServicioAeropuerto entityModified) {
        try {
            Optional<ServicioAeropuerto> entityUpdated = servicioService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                ServicioAeropuertoDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), ServicioAeropuertoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
