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
import org.una.aerolinea.dto.HorarioDTO;
import org.una.aerolinea.entities.Horario;
import org.una.aerolinea.services.IHorarioService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/horarios") 
@Api(tags = {"Horarios"})
public class HorarioController {
    @Autowired
    private IHorarioService horarioService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los horarios", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Horario>> resultadoFound = horarioService.findAll();
            if (resultadoFound.isPresent()) {
                List<HorarioDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un horario por su id", response = HorarioDTO.class, tags = "Horarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Horario> resultadoFound = horarioService.findById(id);
            if (resultadoFound.isPresent()) {
                HorarioDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de roles por estado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Horario>> resultadoFound = horarioService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<HorarioDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/empleado/{term}") 
    @ApiOperation(value = "Obtiene una lista de horarios por empleado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByEmpleado(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Horario>> resultadoFound = horarioService.findByEmpleado(term);
            if (resultadoFound.isPresent()) {
                List<HorarioDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/empleadohorario/{term1}/{term2}") 
    @ApiOperation(value = "Obtiene una lista de horarios por empleado y estado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByEmpleadoAndEstado(@PathVariable(value = "term1") Long term1, @PathVariable(value = "term2") boolean term2) {
        try {
            Optional<List<Horario>> resultadoFound = horarioService.findByEmpleadoAndEstado(term1, term2);
            if (resultadoFound.isPresent()) {
                List<HorarioDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), HorarioDTO.class);
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
    @ApiOperation(value = "Crea un horario", response = HorarioDTO.class, tags = "Horarios")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Horario horario) {
        try {
            Horario entityCreated = horarioService.create(horario);
            HorarioDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, HorarioDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un horario", response = HorarioDTO.class, tags = "Horarios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Horario entityModified) {
        try {
            Optional<Horario> entityUpdated = horarioService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                HorarioDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), HorarioDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
