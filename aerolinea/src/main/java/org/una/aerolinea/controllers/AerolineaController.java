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
   
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
   
    @Autowired
    private IAerolineaService aerolineaService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todas las aerolineas", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasAuthority('gestor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(aerolineaService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una aerolinea por su id", response = AerolineaDTO.class, tags = "Aerolineas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(aerolineaService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de aerolineas por nombre", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(aerolineaService.findByNombreContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/responsable/{term}") 
    @ApiOperation(value = "Obtiene una lista de aerolineas por responsable", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByResponsableAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(aerolineaService.findByResponsableContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de aerolineas por estado", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    @PreAuthorize("hasAuthority('gestor')")
	
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean estado) {
        try {
            return new ResponseEntity(aerolineaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea una aerolinea", response = AerolineaDTO.class, tags = "Aerolineas")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> create(@RequestBody AerolineaDTO tramites,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(aerolineaService.create(tramites), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    

    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una aerolinea", response = AerolineaDTO.class, tags = "Aerolineas")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AerolineaDTO tramitesModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AerolineaDTO> tramiteRegUpdated = aerolineaService.update(tramitesModified, id);
                if (tramiteRegUpdated.isPresent()) {
                    return new ResponseEntity(tramiteRegUpdated, HttpStatus.OK);
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
