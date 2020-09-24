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
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los vuelos", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Vuelo>> resultadoFound = vueloService.findAll();
            if (resultadoFound.isPresent()) {
                List<VueloDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), VueloDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un vuelo por su id", response = VueloDTO.class, tags = "Vuelos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Vuelo> resultadoFound = vueloService.findById(id);
            if (resultadoFound.isPresent()) {
                VueloDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), VueloDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por estado", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Vuelo>> resultadoFound = vueloService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<VueloDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), VueloDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/avion/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por avion", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByAvion(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Vuelo>> resultadoFound = vueloService.findByAvion(term);
            if (resultadoFound.isPresent()) {
                List<VueloDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), VueloDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/ruta/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por ruta", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByRuta(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Vuelo>> resultadoFound = vueloService.findByRuta(term);
            if (resultadoFound.isPresent()) {
                List<VueloDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), VueloDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/fecha/{term}") 
    @ApiOperation(value = "Obtiene una lista de los vuelos por fecha", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByFecha(@PathVariable(value = "term") @JsonbDateFormat(value = "yyyy-MM-dd") Date term) {
        try {
            Optional<List<Vuelo>> resultadoFound = vueloService.findByFecha(term);
            if (resultadoFound.isPresent()) {
                List<VueloDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), VueloDTO.class);
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
    @ApiOperation(value = "Crea un vuelo", response = VueloDTO.class, tags = "Vuelos")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Vuelo vuelo) {
        try {
            Vuelo entityCreated = vueloService.create(vuelo);
            VueloDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, VueloDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un vuelo", response = VueloDTO.class, tags = "Vuelos")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Vuelo entityModified) {
        try {
            Optional<Vuelo> entityUpdated = vueloService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                VueloDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), VueloDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
