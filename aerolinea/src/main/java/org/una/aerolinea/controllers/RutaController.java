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
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todas las rutas", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Ruta>> resultadoFound = rutaService.findAll();
            if (resultadoFound.isPresent()) {
                List<RutaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RutaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una ruta por su id", response = RutaDTO.class, tags = "Rutas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Ruta> resultadoFound = rutaService.findById(id);
            if (resultadoFound.isPresent()) {
                RutaDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), RutaDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de rutas por estado", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Ruta>> resultadoFound = rutaService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<RutaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RutaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/distanciaRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de rutas por rango de distancia", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    public ResponseEntity<?> findByDistanciaRango(@PathVariable(value = "mas") float mas, @PathVariable(value = "menos") float menos) {
        try {
            Optional<List<Ruta>> resultadoFound = rutaService.findByDistanciaRango(mas, menos);
            if (resultadoFound.isPresent()) {
                List<RutaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RutaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/origen/{term}") 
    @ApiOperation(value = "Obtiene una lista de rutas por su origen", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    public ResponseEntity<?> findByOrigenAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Ruta>> resultadoFound = rutaService.findByOrigenContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<RutaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RutaDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/destino/{term}") 
    @ApiOperation(value = "Obtiene una lista de rutas por su destino", response = RutaDTO.class, responseContainer = "List", tags = "Rutas")
    public ResponseEntity<?> findByDestinoAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Ruta>> resultadoFound = rutaService.findByDestinoContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<RutaDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RutaDTO.class);
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
    @ApiOperation(value = "Crea una ruta", response = RutaDTO.class, tags = "Rutas")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Ruta ruta) {
        try {
            Ruta entityCreated = rutaService.create(ruta);
            RutaDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, RutaDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica una ruta", response = RutaDTO.class, tags = "Rutas")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Ruta entityModified) {
        try {
            Optional<Ruta> entityUpdated = rutaService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                RutaDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), RutaDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
