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
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.services.IRolService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/roles") 
@Api(tags = {"Roles"})
public class RolController {
    @Autowired
    private IRolService rolService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los roles", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Rol>> resultadoFound = rolService.findAll();
            if (resultadoFound.isPresent()) {
                List<RolDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RolDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un rol por su id", response = RolDTO.class, tags = "Roles")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Rol> resultadoFound = rolService.findById(id);
            if (resultadoFound.isPresent()) {
                RolDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), RolDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de roles por nombre", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Rol>> resultadoFound = rolService.findByNombreContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<RolDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RolDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de roles por descripcion", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Rol>> resultadoFound = rolService.findByDescripcionContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<RolDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RolDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de roles por estado", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Rol>> resultadoFound = rolService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<RolDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), RolDTO.class);
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
    @ApiOperation(value = "Crea un rol", response = RolDTO.class, tags = "Roles")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Rol rol) {
        try {
            Rol entityCreated = rolService.create(rol);
            RolDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, RolDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un rol", response = RolDTO.class, tags = "Roles")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Rol entityModified) {
        try {
            Optional<Rol> entityUpdated = rolService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                RolDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), RolDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
