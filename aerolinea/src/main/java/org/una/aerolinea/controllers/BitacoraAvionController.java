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
import org.una.aerolinea.dto.BitacoraAvionDTO;
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.entities.BitacoraAvion;
import org.una.aerolinea.services.IBitacoraAvionService;
/**
 *
 * @author Luis
 */

@RestController
@RequestMapping("/bitacoras_aviones") 
@Api(tags = {"Bitacoras_Aviones"})
public class BitacoraAvionController {
  
final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

       
@Autowired
private IBitacoraAvionService avionEstadoService;
    
   @GetMapping("/") 
   @ApiOperation(value = "Obtiene una lista de todos estados de los aviones", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
   public @ResponseBody
   @PreAuthorize("hasAuthority('gestor')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(avionEstadoService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
   
   
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un estado de avion por su id", response = BitacoraAvionDTO.class, tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(avionEstadoService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/combustible/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de los aviones", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByCombustible(@PathVariable(value = "term") int term) {
        try {
            return new ResponseEntity(avionEstadoService.findByCombustible(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/distancia/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de aviones por su distancia recorrida", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByDistanciaRecorrida(@PathVariable(value = "term") int term) {
        try {
            return new ResponseEntity(avionEstadoService.findByDistanciaRecorrida(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de los aviones por estado", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity(avionEstadoService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/tiempo/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de aviones por su tiempo en tierra", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByTiempoTierra(@PathVariable(value = "term") int term) {
        try {
            return new ResponseEntity(avionEstadoService.findByTiempoTierra(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/ubicacion/{term}") 
    @ApiOperation(value = "Obtiene una lista de los estados de aviones por su ubicacion", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByUbicacion(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(avionEstadoService.findByUbicacionContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/avion/{term}") 
    @ApiOperation(value = "Obtiene una lista de bitacora por avion", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByAvion(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity(avionEstadoService.findByAvion(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/distanciaRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de bitacoras de aviones por rango de distancia", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByDistanciaRango(@PathVariable(value = "mas") float mas, @PathVariable(value = "menos") float menos) {
          try {
            return new ResponseEntity<>(avionEstadoService.findByDistanciaRecorridaRango(mas, menos), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/combustibleRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de bitacoras de aviones por rango de combustible", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByCombustibleRango(@PathVariable(value = "mas") int mas, @PathVariable(value = "menos") int menos) {
          try {
            return new ResponseEntity<>(avionEstadoService.findByCombustibleRango(mas, menos), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/tiempoRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de bitacoras de aviones por rango de tiempo en tierra", response = BitacoraAvionDTO.class, responseContainer = "List", tags = "Bitacoras_Aviones")
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> findByTiempoRango(@PathVariable(value = "mas") int mas, @PathVariable(value = "menos") int menos) {
          try {
            return new ResponseEntity<>(avionEstadoService.findByTiempoTierraRango(mas, menos), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea una bitacora de un avion", response = BitacoraAvionDTO.class, tags = "Bitacoras_Aviones")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> create(@RequestBody BitacoraAvionDTO bitacora,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(avionEstadoService.create(bitacora), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un tipo de servicio del aeropuerto", response = BitacoraAvionDTO.class, tags = "Bitacoras_Aviones")
    @ResponseBody
    @PreAuthorize("hasAuthority('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody BitacoraAvionDTO bitacoraModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<BitacoraAvionDTO> bitacoraUpdated = avionEstadoService.update(bitacoraModified, id);
                if (bitacoraUpdated.isPresent()) {
                    return new ResponseEntity(bitacoraUpdated, HttpStatus.OK);
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
