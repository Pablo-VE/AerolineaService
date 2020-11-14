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
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los trabajos de los empleados", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    public @ResponseBody
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(trabajoService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un trabajo de un empleado por su id", response = TrabajoEmpleadoDTO.class, tags = "Trabajos_Empleados")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(trabajoService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de trabajos de empleados por estado", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(trabajoService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/empleado/{term}") 
    @ApiOperation(value = "Obtiene una lista de trabajos de empleados por empleado", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEmpleado(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(trabajoService.findByEmpleado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/areaTrabajo/{term}") 
    @ApiOperation(value = "Obtiene una lista de trabajos de empleados por area de trabajo", response = TrabajoEmpleadoDTO.class, responseContainer = "List", tags = "Trabajos_Empleados")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByAreaTrabajo(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(trabajoService.findByAreaTrabajo(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un trabajo de un empleado", response = TrabajoEmpleadoDTO.class, tags = "Trabajos_Empleados")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> create(@RequestBody TrabajoEmpleadoDTO roles,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(trabajoService.create(roles), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un trabajo de un empleado", response = TrabajoEmpleadoDTO.class, tags = "Trabajos_Empleados")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TrabajoEmpleadoDTO trabajoEmpModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TrabajoEmpleadoDTO> trabajoEmpUpdated = trabajoService.update(trabajoEmpModified, id);
                if (trabajoEmpUpdated.isPresent()) {
                    return new ResponseEntity(trabajoEmpUpdated, HttpStatus.OK);
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
