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
import org.una.aerolinea.dto.AvionDTO;
import org.una.aerolinea.entities.Avion;
import org.una.aerolinea.services.IAvionService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/aviones") 
@Api(tags = {"Aviones"})
public class AvionController {
    @Autowired
    private IAvionService avionService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los aviones", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Avion>> resultadoFound = avionService.findAll();
            if (resultadoFound.isPresent()) {
                List<AvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un avion por su id", response = AvionDTO.class, tags = "Aviones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Avion> resultadoFound = avionService.findById(id);
            if (resultadoFound.isPresent()) {
                AvionDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), AvionDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/matricula/{term}") 
    @ApiOperation(value = "Obtiene un avion por su matricula", response = AvionDTO.class, tags = "Aviones")
    public ResponseEntity<?> findByMatricula(@PathVariable(value = "term") String term) {
        try {

            Optional<Avion> resultadoFound = avionService.findByMatricula(term);
            if (resultadoFound.isPresent()) {
                AvionDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), AvionDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/matricula/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por matricula", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByMatriculaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Avion>> resultadoFound = avionService.findByMatriculaContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<AvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por estado", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Avion>> resultadoFound = avionService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<AvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/aerolinea/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por aerolinea", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByAerolinea(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Avion>> resultadoFound = avionService.findByAerolinea(term);
            if (resultadoFound.isPresent()) {
                List<AvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/tipoAvion/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por tipo de avion", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByTipoAvion(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Avion>> resultadoFound = avionService.findByTipoAvion(term);
            if (resultadoFound.isPresent()) {
                List<AvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AvionDTO.class);
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
    @ApiOperation(value = "Crea un avion", response = AvionDTO.class, tags = "Aviones")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Avion avion) {
        try {
            Avion entityCreated = avionService.create(avion);
            AvionDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, AvionDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un avion", response = AvionDTO.class, tags = "Aviones")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Avion entityModified) {
        try {
            Optional<Avion> entityUpdated = avionService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                AvionDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), AvionDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
