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
import org.una.aerolinea.dto.TrabajoEmpleadoDTO;
import org.una.aerolinea.entities.TrabajoEmpleado;
import org.una.aerolinea.services.ITrabajoEmpleadoService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/trabajos_empleados") 
@Api(tags = {"Trabajos_Empleados"})
public class TrabajoEmpleadoController {
    @Autowired
    private ITrabajoEmpleadoService trabajoService;
    
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los trabajos de los empleados", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TrabajoEmpleado>> resultadoFound = trabajoService.findAll();
            if (resultadoFound.isPresent()) {
                List<TrabajoEmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TrabajoEmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un trabajo de un empleado por su id", response = TrabajoEmpleadoDTO.class, tags = "Trabajos_Empleados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TrabajoEmpleado> resultadoFound = trabajoService.findById(id);
            if (resultadoFound.isPresent()) {
                TrabajoEmpleadoDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), TrabajoEmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de trabajos de empleados por estado", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<TrabajoEmpleado>> resultadoFound = trabajoService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<TrabajoEmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TrabajoEmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/empleado/{term}") 
    @ApiOperation(value = "Obtiene una lista de trabajos de empleados por empleado", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    public ResponseEntity<?> findByEmpleado(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<TrabajoEmpleado>> resultadoFound = trabajoService.findByEmpleado(term);
            if (resultadoFound.isPresent()) {
                List<TrabajoEmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TrabajoEmpleadoDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/areaTrabajo/{term}") 
    @ApiOperation(value = "Obtiene una lista de trabajos de empleados por area de trabajo", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    public ResponseEntity<?> findByAreaTrabajo(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<TrabajoEmpleado>> resultadoFound = trabajoService.findByAreaTrabajo(term);
            if (resultadoFound.isPresent()) {
                List<TrabajoEmpleadoDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), TrabajoEmpleadoDTO.class);
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
    @ApiOperation(value = "Crea un trabajo de un empleado", response = TrabajoEmpleadoDTO.class, tags = "Trabajos_Empleados")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TrabajoEmpleado trabajo) {
        try {
            TrabajoEmpleado entityCreated = trabajoService.create(trabajo);
            TrabajoEmpleadoDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, TrabajoEmpleadoDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un trabajo de un empleado", response = TrabajoEmpleadoDTO.class, tags = "Trabajos_Empleados")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TrabajoEmpleado entityModified) {
        try {
            Optional<TrabajoEmpleado> entityUpdated = trabajoService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                TrabajoEmpleadoDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), TrabajoEmpleadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
