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
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las transacciones", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(transaccionService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una transaccion por su id", response = TransaccionDTO.class, tags = "Transacciones")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(transaccionService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por descripcion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(transaccionService.findByDescripcionContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/lugar/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por lugar", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByLugarAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(transaccionService.findByLugarContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por estado", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(transaccionService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/rol/{term}") 
    @ApiOperation(value = "Obtiene una lista de transacciones por su rol", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByRol(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(transaccionService.findByRol(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> create(@RequestBody TransaccionDTO transacciones,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(transaccionService.create(transacciones), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TransaccionDTO transModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TransaccionDTO> transUpdated = transaccionService.update(transModified, id);
                if (transUpdated.isPresent()) {
                    return new ResponseEntity(transUpdated, HttpStatus.OK);
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
