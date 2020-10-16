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
import org.una.aerolinea.dto.AreaTrabajoDTO;
import org.una.aerolinea.entities.AreaTrabajo;
import org.una.aerolinea.services.IAreaTrabajoService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/areas_trabajos") 
@Api(tags = {"Areas_Trabajos"})
public class AreaTrabajoController {
    @Autowired
    private IAreaTrabajoService areaService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los areas de trabajo", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<AreaTrabajo>> resultadoFound = areaService.findAll();
            if (resultadoFound.isPresent()) {
                List<AreaTrabajoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un area de trabajo por su id", response = AreaTrabajoDTO.class, tags = "Areas_Trabajos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<AreaTrabajo> resultadoFound = areaService.findById(id);
            if (resultadoFound.isPresent()) {
                AreaTrabajoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de areas de trabajo por nombre", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AreaTrabajo>> resultadoFound = areaService.findByNombreContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<AreaTrabajoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de areas de trabajo por descripcion", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AreaTrabajo>> resultadoFound = areaService.findByDescripcionContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<AreaTrabajoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de areas de trabajo por estado", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<AreaTrabajo>> resultadoFound = areaService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<AreaTrabajoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), AreaTrabajoDTO.class);
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
    @ApiOperation(value = "Crea un area de trabajo", response = AreaTrabajoDTO.class, tags = "Areas_Trabajos")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody AreaTrabajo area) {
        try {
            AreaTrabajo entityCreated = areaService.create(area);
            AreaTrabajoDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, AreaTrabajoDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un area de trabajo", response = AreaTrabajoDTO.class, tags = "Areas_Trabajos")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AreaTrabajo entityModified) {
        try {
            Optional<AreaTrabajo> entityUpdated = areaService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                AreaTrabajoDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
