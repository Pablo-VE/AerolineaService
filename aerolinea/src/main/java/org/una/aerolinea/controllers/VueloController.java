/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.json.bind.annotation.JsonbDateFormat;
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
import org.una.aerolinea.dto.VueloDTO;
import org.una.aerolinea.entities.Vuelo;
import org.una.aerolinea.services.IVueloService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/vuelos") 
@Api(tags = {"Vuelos"})
public class VueloController {
    @Autowired
    private IVueloService vueloService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los vuelos", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(vueloService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un vuelo por su id", response = VueloDTO.class, tags = "Vuelos")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(vueloService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por estado", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") int term) {
        try {
            return new ResponseEntity<>(vueloService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/avion/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por avion", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByAvion(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(vueloService.findByAvion(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/ruta/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por ruta", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByRuta(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(vueloService.findByRuta(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/fecha/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por fecha", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByFecha(@PathVariable(value = "term") @JsonbDateFormat(value = "yyyy-MM-dd") Date term) {
        try {
            return new ResponseEntity<>(vueloService.findByFecha(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un vuelo", response = VueloDTO.class, tags = "Vuelos")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> create(@RequestBody VueloDTO roles,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(vueloService.create(roles), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un vuelo", response = VueloDTO.class, tags = "Vuelos")
    @ResponseBody
    @PreAuthorize("hasRole('gestor') or hasRole('gerente')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody VueloDTO vueloModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<VueloDTO> vueloUpdated = vueloService.update(vueloModified, id);
                if (vueloUpdated.isPresent()) {
                    return new ResponseEntity(vueloUpdated, HttpStatus.OK);
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
