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
import org.una.aerolinea.dto.ServicioRegistradoDTO;
import org.una.aerolinea.entities.ServicioRegistrado;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.services.IServicioRegistradoService;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/servicios_aeropuerto") 
@Api(tags = {"Servicios_Aeropuerto"})
public class ServicioRegistradoController {
    @Autowired
    private IServicioRegistradoService servicioService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los servicios del aeropuerto", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ServicioRegistrado>> resultadoFound = servicioService.findAll();
            if (resultadoFound.isPresent()) {
                List<ServicioRegistradoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioRegistradoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un servicio del aeropuerto por su id", response = ServicioRegistradoDTO.class, tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ServicioRegistrado> resultadoFound = servicioService.findById(id);
            if (resultadoFound.isPresent()) {
                ServicioRegistradoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), ServicioRegistradoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los servicios del aeropuerto por estado", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<ServicioRegistrado>> resultadoFound = servicioService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<ServicioRegistradoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioRegistradoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estadoCobro/{term}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por estado de cobro", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByEstadoCobro(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<ServicioRegistrado>> resultadoFound = servicioService.findByEstadoCobro(term);
            if (resultadoFound.isPresent()) {
                List<ServicioRegistradoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioRegistradoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/cobroRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por un rango de cobro", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByCobroRango(@PathVariable(value = "mas") float mas, @PathVariable(value = "menos") float menos) {
        try {
            Optional<List<ServicioRegistrado>> resultadoFound = servicioService.findByCobroRango(mas, menos);
            if (resultadoFound.isPresent()) {
                List<ServicioRegistradoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioRegistradoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/tipo/{term}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por el tipo", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Aeropuerto")
    public ResponseEntity<?> findByTipoAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ServicioRegistrado>> resultadoFound = servicioService.findByTipoContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<ServicioRegistradoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ServicioRegistradoDTO.class);
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
    @ApiOperation(value = "Crea un servicio del aeropuerto", response = ServicioRegistradoDTO.class, tags = "Servicios_Aeropuerto")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ServicioRegistrado servicio) {
        try {
            ServicioRegistrado entityCreated = servicioService.create(servicio);
            ServicioRegistradoDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, ServicioRegistradoDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un servicio del aeropuerto", response = ServicioRegistradoDTO.class, tags = "Servicios_Aeropuerto")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServicioRegistrado entityModified) {
        try {
            Optional<ServicioRegistrado> entityUpdated = servicioService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                ServicioRegistradoDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), ServicioRegistradoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
