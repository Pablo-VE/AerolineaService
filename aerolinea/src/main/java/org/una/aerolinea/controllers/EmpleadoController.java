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
import org.una.aerolinea.dto.EmpleadoDTO;
import org.una.aerolinea.entities.Empleado;
import org.una.aerolinea.services.IEmpleadoService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */

@RestController
@RequestMapping("/empleados") 
@Api(tags = {"Empleados"})
public class EmpleadoController {
    
    @Autowired
    private IEmpleadoService empleadoService;
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los empleados", response = EmpleadoDTO.class, responseContainer = "List", tags = "Empleados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Empleado>> resultadoFound = empleadoService.findAll();
            if (resultadoFound.isPresent()) {
                List<EmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un empleado por su id", response = EmpleadoDTO.class, tags = "Empleados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Empleado> resultadoFound = empleadoService.findById(id);
            if (resultadoFound.isPresent()) {
                EmpleadoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/cedula/{term}") 
    @ApiOperation(value = "Obtiene una lista de empleados por cedula", response = EmpleadoDTO.class, responseContainer = "List", tags = "Empleados")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Empleado>> resultadoFound = empleadoService.findByCedulaContaining(term);
            if (resultadoFound.isPresent()) {
                List<EmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/cedula/{term}") 
    @ApiOperation(value = "Obtiene un empleado por su cedula", response = EmpleadoDTO.class, tags = "Empleados")
    public ResponseEntity<?> findByCedula(@PathVariable(value = "term") String term) {
        try {

            Optional<Empleado> resultadoFound = empleadoService.findByCedula(term);
            if (resultadoFound.isPresent()) {
                EmpleadoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de empleados por nombre", response = EmpleadoDTO.class, responseContainer = "List", tags = "Empleados")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Empleado>> resultadoFound = empleadoService.findByNombreContainingIgnoreCase(term);
            if (resultadoFound.isPresent()) {
                List<EmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de empleados por estado", response = EmpleadoDTO.class, responseContainer = "List", tags = "Empleados")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Empleado>> resultadoFound = empleadoService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<EmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), EmpleadoDTO.class);
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
    @ApiOperation(value = "Crea un empleado", response = EmpleadoDTO.class, tags = "Empleados")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Empleado empleado) {
        try {
            Empleado entityCreated = empleadoService.create(empleado);
            EmpleadoDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, EmpleadoDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un empleado", response = EmpleadoDTO.class, tags = "Empleados")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Empleado entityModified) {
        try {
            Optional<Empleado> entityUpdated = empleadoService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                EmpleadoDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
