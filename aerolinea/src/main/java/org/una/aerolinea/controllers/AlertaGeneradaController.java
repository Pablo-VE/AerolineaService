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
import org.una.aerolinea.services.IAlertaGeneradaService;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.dto.AlertaGeneradaDTO;
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.entities.AlertaGenerada;



/**
 *
 * @author Luis
 */
@RestController
@RequestMapping("/alertas_generadas") 
@Api(tags = {"Alertas_Generadas"})
public class AlertaGeneradaController {

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @Autowired
    private IAlertaGeneradaService alertaGeneradaService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos las alertas generadas", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    public @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(alertaGeneradaService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una alerta generada por su id", response = AlertaGeneradaDTO.class, tags = "Alertas_Generadas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(alertaGeneradaService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
        
    @GetMapping("/list/autorizacion/{term}") 
    @ApiOperation(value = "Obtiene una lista de las alertas por su autorizacion", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByAutorizacion(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(alertaGeneradaService.findByAutorizacion(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @GetMapping("/list/tipoAlerta/{term}") 
    @ApiOperation(value = "Obtiene una lista de alertas por tipo", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")    
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByTipoAlerta(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity(alertaGeneradaService.findByTipoAlerta(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/vuelo/{term}") 
    @ApiOperation(value = "Obtiene una lista de las alertas por vuelos", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByVuelo(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity(alertaGeneradaService.findByVuelo(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por estado", response = AlertaGeneradaDTO.class, responseContainer = "List", tags = "Alertas_Generadas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") int estado) {
        try {
            return new ResponseEntity<>(alertaGeneradaService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un vuelo", response = AlertaGeneradaDTO.class, tags = "Alertas_Generadas")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> create(@RequestBody AlertaGeneradaDTO alerta,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(alertaGeneradaService.create(alerta), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un vuelo", response = AlertaGeneradaDTO.class, tags = "Alertas_Generadas")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AlertaGeneradaDTO alertaModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AlertaGeneradaDTO> alertaUpdated = alertaGeneradaService.update(alertaModified, id);
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
