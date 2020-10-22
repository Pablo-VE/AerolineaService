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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los aviones", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(avionService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un avion por su id", response = AvionDTO.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(avionService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/matricula/{term}") 
    @ApiOperation(value = "Obtiene un avion por su matricula", response = AvionDTO.class, tags = "Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByMatricula(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(avionService.findByMatricula(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/matricula/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por matricula", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByMatriculaAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(avionService.findByMatriculaContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por estado", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(avionService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/aerolinea/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por aerolinea", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByAerolinea(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(avionService.findByAerolinea(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/tipoAvion/{term}") 
    @ApiOperation(value = "Obtiene una lista de aviones por tipo de avion", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByTipoAvion(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(avionService.findByTipoAvion(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un avion", response = AvionDTO.class, tags = "Aviones")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> create(@RequestBody AvionDTO avion,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(avionService.create(avion), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    

    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un avion", response = AvionDTO.class, tags = "Aviones")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AvionDTO entityModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AvionDTO> avionUpdated = avionService.update(entityModified, id);
                if (avionUpdated.isPresent()) {
                    return new ResponseEntity(avionUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    
}
