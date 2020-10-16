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
import org.una.aerolinea.dto.ParametroAplicacionDTO;
import org.una.aerolinea.entities.ParametroAplicacion;
import org.una.aerolinea.services.IParametroAplicacionService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/parametros_aplicacion") 
@Api(tags = {"Parametros_Aplicacion"})
public class ParametroAplicacionController {
    @Autowired
    private IParametroAplicacionService parametroService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los parametros de la aplicacion", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametroAplicacion>> resultadoFound = parametroService.findAll();
            if (resultadoFound.isPresent()) {
                List<ParametroAplicacionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ParametroAplicacionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un parametro de la aplicacion por su id", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ParametroAplicacion> resultadoFound = parametroService.findById(id);
            if (resultadoFound.isPresent()) {
                ParametroAplicacionDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), ParametroAplicacionDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de los parametros de la aplicacion por nombre", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ParametroAplicacion>> resultadoFound = parametroService.findByNombreContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<ParametroAplicacionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ParametroAplicacionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de los parametros de la aplicacion por descripcion", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ParametroAplicacion>> resultadoFound = parametroService.findByDescripcionContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<ParametroAplicacionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ParametroAplicacionDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de roles por estado", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<ParametroAplicacion>> resultadoFound = parametroService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<ParametroAplicacionDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), ParametroAplicacionDTO.class);
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
    @ApiOperation(value = "Crea un parametro de la aplicacion", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ParametroAplicacion parametro) {
        try {
            ParametroAplicacion entityCreated = parametroService.create(parametro);
            ParametroAplicacionDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, ParametroAplicacionDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un parametro de la aplicacion", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametroAplicacion entityModified) {
        try {
            Optional<ParametroAplicacion> entityUpdated = parametroService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                ParametroAplicacionDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), ParametroAplicacionDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
