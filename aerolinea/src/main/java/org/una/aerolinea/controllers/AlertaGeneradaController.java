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
import org.una.aerolinea.services.IAlertaGeneradaService;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.dto.AlertaGeneradaDTO;
import org.una.aerolinea.entities.AlertaGenerada;



/**
 *
 * @author Luis
 */
@RestController
@RequestMapping("/alertas_generadas") 
@Api(tags = {"Alertas_Generadas"})
public class AlertaGeneradaController {
    
    @Autowired
    private IAlertaGeneradaService alertaGeneradaService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos las alertas generadas", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<AlertaGenerada>> resultadoFound = alertaGeneradaService.findAll();
            if (resultadoFound.isPresent()) {
                List<AlertaGeneradaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaGeneradaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una alerta generada por su id", response = AlertaGeneradaDTO.class, tags = "Alertas_Generadas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<AlertaGenerada> resultadoFound = alertaGeneradaService.findById(id);
            if (resultadoFound.isPresent()) {
                AlertaGeneradaDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), AlertaGeneradaDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
        
    @GetMapping("/list/autorizacion/{term}") 
    @ApiOperation(value = "Obtiene una lista de las alertas por su autorizacion", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    public ResponseEntity<?> findByAutorizacion(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AlertaGenerada>> resultadoFound = alertaGeneradaService.findByAutorizacion(term);
            if (resultadoFound.isPresent()) {
                List<AlertaGeneradaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaGeneradaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/list/tipoAlerta/{term}") 
    @ApiOperation(value = "Obtiene una lista de alertas por tipo", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    public ResponseEntity<?> findByTipoAlerta(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<AlertaGenerada>> resultadoFound = alertaGeneradaService.findByTipoAlerta(term);
            if (resultadoFound.isPresent()) {
                List<AlertaGeneradaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaGeneradaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/vuelo/{term}") 
    @ApiOperation(value = "Obtiene una lista de las alertas por vuelos", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    public ResponseEntity<?> findByVuelo(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<AlertaGenerada>> resultadoFound = alertaGeneradaService.findByVuelo(term);
            if (resultadoFound.isPresent()) {
                List<AlertaGeneradaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaGeneradaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por estado", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<AlertaGenerada>> resultadoFound = alertaGeneradaService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<AlertaGeneradaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AlertaGeneradaDTO.class);
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
    @ApiOperation(value = "Crea un vuelo", response = AlertaGeneradaDTO.class, tags = "Alertas_Generadas")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody AlertaGenerada alerta) {
        try {
            AlertaGenerada entityCreated = alertaGeneradaService.create(alerta);
            AlertaGeneradaDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, AlertaGeneradaDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un vuelo", response = AlertaGeneradaDTO.class, tags = "Alertas_Generadas")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AlertaGenerada entityModified) {
        try {
            Optional<AlertaGenerada> entityUpdated = alertaGeneradaService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                AlertaGeneradaDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), AlertaGeneradaDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
