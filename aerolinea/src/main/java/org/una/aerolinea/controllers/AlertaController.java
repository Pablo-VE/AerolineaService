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
import org.una.aerolinea.dto.AlertaDTO;
import org.una.aerolinea.entities.Alerta;
import org.una.aerolinea.services.IAlertaService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/alertas") 
@Api(tags = {"Alertas"})
public class AlertaController {
    @Autowired
    private IAlertaService alertaService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todas las alertas", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Alerta>> resultadoFound = alertaService.findAll();
            if (resultadoFound.isPresent()) {
                List<AlertaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una alerta por su id", response = AlertaDTO.class, tags = "Alertas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Alerta> resultadoFound = alertaService.findById(id);
            if (resultadoFound.isPresent()) {
                AlertaDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), AlertaDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de alertas por estado", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Alerta>> resultadoFound = alertaService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<AlertaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/vuelo/{term}") 
    @ApiOperation(value = "Obtiene una alerta por vuelo", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    public ResponseEntity<?> findByVuelo(@PathVariable(value = "term") Long term) {
        try {
            Optional<Alerta> resultadoFound = alertaService.findByVuelo(term);
            if (resultadoFound.isPresent()) {
                AlertaDTO resultadoDTO = MapperUtils.DtoFromEntity(resultadoFound.get(), AlertaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de alertas por descripcion", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Alerta>> resultadoFound = alertaService.findByDescripcionContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<AlertaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaDTO.class);
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
    @ApiOperation(value = "Crea una alerta", response = AlertaDTO.class, tags = "Alertas")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Alerta alerta) {
        try {
            Alerta entityCreated = alertaService.create(alerta);
            AlertaDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, AlertaDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una alerta", response = AlertaDTO.class, tags = "Alertas")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Alerta entityModified) {
        try {
            Optional<Alerta> entityUpdated = alertaService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                AlertaDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), AlertaDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
