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
   
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los horarios", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(horarioService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un horario por su id", response = HorarioDTO.class, tags = "Horarios")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(horarioService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de roles por estado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(horarioService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/empleado/{term}") 
    @ApiOperation(value = "Obtiene una lista de horarios por empleado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEmpleado(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(horarioService.findByEmpleado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/empleadohorario/{term1}/{term2}") 
    @ApiOperation(value = "Obtiene una lista de horarios por empleado y estado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEmpleadoAndEstado(@PathVariable(value = "term1") Long term1, @PathVariable(value = "term2") boolean term2) {
        try {
            return new ResponseEntity<>(horarioService.findByEmpleadoAndEstado(term1, term2), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un horario", response = HorarioDTO.class, tags = "Horarios")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> create(@RequestBody HorarioDTO horario,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(horarioService.create(horario), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un horario", response = HorarioDTO.class, tags = "Horarios")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HorarioDTO horarioModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<HorarioDTO> horarioUpdated = horarioService.update(horarioModified, id);
                if (horarioUpdated.isPresent()) {
                    return new ResponseEntity(horarioUpdated, HttpStatus.OK);
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
