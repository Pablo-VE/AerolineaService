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
import org.una.aerolinea.dto.AerolineaDTO;
import org.una.aerolinea.entities.Aerolinea;
import org.una.aerolinea.services.IAerolineaService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/aerolineas") 
@Api(tags = {"Aerolineas"})
public class AerolineaController {
    @Autowired
    private IAerolineaService aerolineaService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Aerolinea>> resultadoFound = aerolineaService.findAll();
            if (resultadoFound.isPresent()) {
                List<AerolineaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AerolineaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una aerolinea por su id", response = AerolineaDTO.class, tags = "Aerolineas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Aerolinea> resultadoFound = aerolineaService.findById(id);
            if (resultadoFound.isPresent()) {
                AerolineaDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), AerolineaDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de aerolineas por nombre", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Aerolinea>> resultadoFound = aerolineaService.findByNombreContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<AerolineaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AerolineaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/responsable/{term}") 
    @ApiOperation(value = "Obtiene una lista de aerolineas por responsable", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByResponsableAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Aerolinea>> resultadoFound = aerolineaService.findByResponsableContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<AerolineaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AerolineaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de aerolineas por estado", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Aerolinea>> resultadoFound = aerolineaService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<AerolineaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AerolineaDTO.class);
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
    @ApiOperation(value = "Crea una aerolinea", response = AerolineaDTO.class, tags = "Aerolineas")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Aerolinea aerolinea) {
        try {
            Aerolinea entityCreated = aerolineaService.create(aerolinea);
            AerolineaDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, AerolineaDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una aerolinea", response = AerolineaDTO.class, tags = "Aerolineas")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Aerolinea entityModified) {
        try {
            Optional<Aerolinea> entityUpdated = aerolineaService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                AerolineaDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), AerolineaDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
