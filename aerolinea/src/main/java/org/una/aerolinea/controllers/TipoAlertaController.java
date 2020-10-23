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
import org.una.aerolinea.dto.TipoAlertaDTO;
import org.una.aerolinea.entities.TipoAlerta;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.services.ITipoAlertaService;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/alertas") 
@Api(tags = {"Alertas"})
public class TipoAlertaController {
    @Autowired
    private ITipoAlertaService alertaService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
 
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las alertas", response = TipoAlertaDTO.class, responseContainer = "List", tags = "Alertas")
    @PreAuthorize("hasAuthority('gestor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(alertaService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una alerta por su id", response = TipoAlertaDTO.class, tags = "Alertas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(alertaService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de alertas por estado", response = TipoAlertaDTO.class, responseContainer = "List", tags = "Alertas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(alertaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de alertas por descripcion", response = TipoAlertaDTO.class, responseContainer = "List", tags = "Alertas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(alertaService.findByDescripcionContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea una alerta", response = TipoAlertaDTO.class, tags = "Alertas")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> create(@RequestBody TipoAlertaDTO alerta,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(alertaService.create(alerta), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una alerta", response = TipoAlertaDTO.class, tags = "Alertas")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TipoAlertaDTO alertaModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TipoAlertaDTO> alertaUpdated = alertaService.update(alertaModified, id);
                if (alertaUpdated.isPresent()) {
                    return new ResponseEntity(alertaUpdated, HttpStatus.OK);
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
