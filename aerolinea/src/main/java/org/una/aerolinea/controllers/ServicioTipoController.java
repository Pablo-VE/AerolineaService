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
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.dto.ServicioTipoDTO;
import org.una.aerolinea.entities.ServicioTipo;
import org.una.aerolinea.services.IServicioTipoService;

/**
 *
 * @author Luis
 */
@RestController
@RequestMapping("/tipos_servicios_aeropuerto") 
@Api(tags = {"Tipos_Servicios_Aeropuerto"})
public class ServicioTipoController {
      
@Autowired
private IServicioTipoService tipoServiAeroService;

final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

   @GetMapping("/") 
   @ApiOperation(value = "Obtiene una lista de todos los tipos de servicios del aeropuerto", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
   @PreAuthorize("hasAuthority('gestor')")
   public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(tipoServiAeroService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un tipo de servicio del aeropuerto por su id", response = ServicioTipoDTO.class, tags = "Tipos_Servicios_Aeropuerto")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tipoServiAeroService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los tipos de servicios del aeropuerto por estado", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(tipoServiAeroService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/descripcion/{descripcion}") 
    @ApiOperation(value = "Obtiene una lista de tipos de servicios del aeropuerto por su descripcion", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "descripcion") String descripcion) {
        try {
            return new ResponseEntity<>(tipoServiAeroService.findByDescripcion(descripcion), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de tipos de aviones por su nombre", response = ServicioTipoDTO.class, responseContainer = "List", tags = "Tipos_Servicios_Aeropuerto")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(tipoServiAeroService.findByNombreContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
       
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un tipo de servicio del aeropuerto", response = ServicioTipoDTO.class, tags = "Tipos_Servicios_Aeropuerto")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> create(@RequestBody ServicioTipoDTO servicioT,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tipoServiAeroService.create(servicioT), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un tipo de servicio del aeropuerto", response = ServicioTipoDTO.class, tags = "Tipos_Servicios_Aeropuerto")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServicioTipoDTO servicioModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ServicioTipoDTO> servicioUpdated = tipoServiAeroService.update(servicioModified, id);
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
