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
import org.una.aerolinea.dto.RutaDTO;
import org.una.aerolinea.entities.Ruta;
import org.una.aerolinea.services.IRutaService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/rutas") 
@Api(tags = {"Rutas"})
public class RutaController {
    @Autowired
    private IRutaService rutaService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las rutas", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(rutaService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una ruta por su id", response = RutaDTO.class, tags = "Rutas")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(rutaService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de rutas por estado", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(rutaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/distanciaRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de rutas por rango de distancia", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByDistanciaRango(@PathVariable(value = "mas") float mas, @PathVariable(value = "menos") float menos) {
          try {
            return new ResponseEntity<>(rutaService.findByDistanciaRango(mas, menos), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/origen/{term}") 
    @ApiOperation(value = "Obtiene una lista de rutas por su origen", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByOrigenAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(rutaService.findByOrigenContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/destino/{term}") 
    @ApiOperation(value = "Obtiene una lista de rutas por su destino", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByDestinoAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(rutaService.findByDestinoContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea una ruta", response = RutaDTO.class, tags = "Rutas")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> create(@RequestBody RutaDTO ruta,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(rutaService.create(ruta), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una ruta", response = RutaDTO.class, tags = "Rutas")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RutaDTO rutaModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<RutaDTO> rutaUpdated = rutaService.update(rutaModified, id);
                if (rutaUpdated.isPresent()) {
                    return new ResponseEntity(rutaUpdated, HttpStatus.OK);
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
