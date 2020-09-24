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
import org.una.aerolinea.dto.HoraMarcajeDTO;
import org.una.aerolinea.entities.HoraMarcaje;
import org.una.aerolinea.services.IHoraMarcajeService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/horas_marcajes") 
@Api(tags = {"Horas_Marcajes"})
public class HoraMarcajeController {
    @Autowired
    private IHoraMarcajeService marcajeService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos las horas de marcaje", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Horas_Marcajes")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<HoraMarcaje>> resultadoFound = marcajeService.findAll();
            if (resultadoFound.isPresent()) {
                List<HoraMarcajeDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una hora de marcaje por su id", response = HoraMarcajeDTO.class, tags = "Horas_Marcajes")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<HoraMarcaje> resultadoFound = marcajeService.findById(id);
            if (resultadoFound.isPresent()) {
                HoraMarcajeDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/tipo/{term}") 
    @ApiOperation(value = "Obtiene una lista de horas de marcaje por tipo", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Horas_Marcajes")
    public ResponseEntity<?> findByTipoAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<HoraMarcaje>> resultadoFound = marcajeService.findByTipoContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<HoraMarcajeDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de horas de marcaje por estado", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Horas_Marcajes")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<HoraMarcaje>> resultadoFound = marcajeService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<HoraMarcajeDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), HoraMarcajeDTO.class);
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
    @ApiOperation(value = "Crea una hora de marcaje", response = HoraMarcajeDTO.class, tags = "Horas_Marcajes")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody HoraMarcaje horamarcaje) {
        try {
            HoraMarcaje entityCreated = marcajeService.create(horamarcaje);
            HoraMarcajeDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, HoraMarcajeDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una hora de marcaje", response = HoraMarcajeDTO.class, tags = "Horas_Marcajes")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HoraMarcaje entityModified) {
        try {
            Optional<HoraMarcaje> entityUpdated = marcajeService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                HoraMarcajeDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
