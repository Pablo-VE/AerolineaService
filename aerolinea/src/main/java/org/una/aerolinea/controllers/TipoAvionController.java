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
import org.una.aerolinea.dto.TipoAvionDTO;
import org.una.aerolinea.entities.TipoAvion;
import org.una.aerolinea.services.ITipoAvionService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/tipos_aviones") 
@Api(tags = {"Tipos_Aviones"})
public class TipoAvionController {
    @Autowired
    private ITipoAvionService tipoService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los tipos de aviones", response = TipoAvionDTO.class, responseContainer = "List", tags = "Tipos_Aviones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TipoAvion>> resultadoFound = tipoService.findAll();
            if (resultadoFound.isPresent()) {
                List<TipoAvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TipoAvionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un tipo de avion por su id", response = TipoAvionDTO.class, tags = "Tipos_Aviones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TipoAvion> resultadoFound = tipoService.findById(id);
            if (resultadoFound.isPresent()) {
                TipoAvionDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), TipoAvionDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de los tipos de aviones por estado", response = TipoAvionDTO.class, responseContainer = "List", tags = "Tipos_Aviones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<TipoAvion>> resultadoFound = tipoService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<TipoAvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TipoAvionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/distanciaRango/{mas}/{menos}") 
    @ApiOperation(value = "Obtiene una lista de tipos de aviones por rango de distancia recomendada", response = TipoAvionDTO.class, responseContainer = "List", tags = "Tipos_Aviones")
    public ResponseEntity<?> findByDistanciaRango(@PathVariable(value = "mas") float mas, @PathVariable(value = "menos") float menos) {
        try {
            Optional<List<TipoAvion>> resultadoFound = tipoService.findByDistanciaRango(mas, menos);
            if (resultadoFound.isPresent()) {
                List<TipoAvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TipoAvionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de tipos de aviones por su nombre", response = TipoAvionDTO.class, responseContainer = "List", tags = "Tipos_Aviones")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<TipoAvion>> resultadoFound = tipoService.findByNombreContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<TipoAvionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TipoAvionDTO.class);
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
    @ApiOperation(value = "Crea un tipo de avion", response = TipoAvionDTO.class, tags = "Tipos_Aviones")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TipoAvion tipoAvion) {
        try {
            TipoAvion entityCreated = tipoService.create(tipoAvion);
            TipoAvionDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, TipoAvionDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un tipo de avion", response = TipoAvionDTO.class, tags = "Tipos_Aviones")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TipoAvion entityModified) {
        try {
            Optional<TipoAvion> entityUpdated = tipoService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                TipoAvionDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), TipoAvionDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
