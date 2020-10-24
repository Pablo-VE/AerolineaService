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
import org.una.aerolinea.dto.ServicioRegistradoDTO;
import org.una.aerolinea.entities.ServicioRegistrado;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.services.IServicioRegistradoService;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/servicios_registrados") 
@Api(tags = {"Servicios_Registrados"})
public class ServicioRegistradoController {
    @Autowired
    private IServicioRegistradoService servicioService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los servicios del aeropuerto", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Registrados")
    @PreAuthorize("hasAuthority('gestor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(servicioService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un servicio del aeropuerto por su id", response = ServicioRegistradoDTO.class, tags = "Servicios_Registrados")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(servicioService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los servicios del aeropuerto por estado", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Registrados")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(servicioService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estadoCobro/{term}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por estado de cobro", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Registrados")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByEstadoCobro(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(servicioService.findByEstadoCobro(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/cobroRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por un rango de cobro", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Registrados")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByCobroRango(@PathVariable(value = "mas") float mas, @PathVariable(value = "menos") float menos) {
        try {
            return new ResponseEntity<>(servicioService.findByCobroRango(mas, menos), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/tipo/{term}") 
    @ApiOperation(value = "Obtiene una lista de servicios del aeropuerto por el tipo", response = ServicioRegistradoDTO.class, responseContainer = "List", tags = "Servicios_Registrados")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByTipoAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(servicioService.findByTipoContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un servicio del avion", response = ServicioRegistradoDTO.class, tags = "Servicios_Registrados")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> create(@RequestBody ServicioRegistradoDTO servicio,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(servicioService.create(servicio), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un servicio del aeropuerto", response = ServicioRegistradoDTO.class, tags = "Servicios_Registrados")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServicioRegistradoDTO servicioModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ServicioRegistradoDTO> servicioUpdated = servicioService.update(servicioModified, id);
                if (servicioUpdated.isPresent()) {
                    return new ResponseEntity(servicioUpdated, HttpStatus.OK);
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
