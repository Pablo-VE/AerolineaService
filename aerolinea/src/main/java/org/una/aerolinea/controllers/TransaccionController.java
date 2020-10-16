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
import org.una.aerolinea.dto.TransaccionDTO;
import org.una.aerolinea.entities.Transaccion;
import org.una.aerolinea.services.ITransaccionService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/transacciones") 
@Api(tags = {"Transacciones"})
public class TransaccionController {
    @Autowired
    private ITransaccionService transaccionService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las transacciones", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Transaccion>> resultadoFound = transaccionService.findAll();
            if (resultadoFound.isPresent()) {
                List<TransaccionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una transaccion por su id", response = TransaccionDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Transaccion> resultadoFound = transaccionService.findById(id);
            if (resultadoFound.isPresent()) {
                TransaccionDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por descripcion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Transaccion>> resultadoFound = transaccionService.findByDescripcionContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<TransaccionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/lugar/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por lugar", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByLugarAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Transaccion>> resultadoFound = transaccionService.findByLugarContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<TransaccionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por estado", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Transaccion>> resultadoFound = transaccionService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<TransaccionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/rol/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por su rol", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByRol(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Transaccion>> resultadoFound = transaccionService.findByRol(term);
            if (resultadoFound.isPresent()) {
                List<TransaccionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TransaccionDTO.class);
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
    @ApiOperation(value = "Crea una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Transaccion transaccion) {
        try {
            Transaccion entityCreated = transaccionService.create(transaccion);
            TransaccionDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, TransaccionDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Transaccion entityModified) {
        try {
            Optional<Transaccion> entityUpdated = transaccionService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                TransaccionDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), TransaccionDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
}
