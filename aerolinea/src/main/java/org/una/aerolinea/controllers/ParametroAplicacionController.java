/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.una.aerolinea.dto.ParametroAplicacionDTO;
import org.una.aerolinea.services.IParametroAplicacionService;

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
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/") 
    @ApiOperation(value = "Obtiene una lista de todos los parametros de la aplicacion", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(parametroService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un parametro de la aplicacion por su id", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(parametroService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/nombre/{term}") 
    @ApiOperation(value = "Obtiene una lista de los parametros de la aplicacion por nombre", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByNombreAproximate(@PathVariable(value = "term") String term) {
        try {
            
            return new ResponseEntity<>(parametroService.findByNombreContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombreValor/{nombre}/{valor}") 
    @ApiOperation(value = "Obtiene un parametro por su nombre y valor", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByNombreAndValor(@PathVariable(value = "nombre") String nombre, @PathVariable(value = "valor") String valor) {
        try {
            return new ResponseEntity<>(parametroService.findByNombreAndValor(nombre, valor), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/descripcion/{term}") 
    @ApiOperation(value = "Obtiene una lista de los parametros de la aplicacion por descripcion", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByDescripcionAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(parametroService.findByDescripcionContainingIgnoreCase(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de roles por estado", response = ParametroAplicacionDTO.class, responseContainer = "List", tags = "Parametros_Aplicacion")
    @PreAuthorize("hasRole('gestor') or hasRole('auditor')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(parametroService.findByEstado(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un parametro de la aplicacion", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> create(@RequestBody ParametroAplicacionDTO parametros,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(parametroService.create(parametros), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crearAutorizacion") 
    @ApiOperation(value = "Crea un parametro de la aplicacion con contraseña sensible", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> createPasswordAutorizacion(@RequestBody ParametroAplicacionDTO parametros,  BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(parametroService.createPasswordAutorizacion(parametros), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/modificarAutorizacion/{id}") 
    @ApiOperation(value = "Modifica un parametro de la aplicacion con contraseña sensible", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> updatePasswordAutorizacion(@PathVariable(value = "id") Long id, @RequestBody ParametroAplicacionDTO parametrosModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ParametroAplicacionDTO> parametrosUpdated = parametroService.updatePasswordAutorizacion(parametrosModified, id);
                if (parametrosUpdated.isPresent()) {
                    return new ResponseEntity(parametrosUpdated, HttpStatus.OK);
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
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un parametro de la aplicacion", response = ParametroAplicacionDTO.class, tags = "Parametros_Aplicacion")
    @ResponseBody
    @PreAuthorize("hasRole('gestor')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametroAplicacionDTO parametrosModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ParametroAplicacionDTO> parametrosUpdated = parametroService.update(parametrosModified, id);
                if (parametrosUpdated.isPresent()) {
                    return new ResponseEntity(parametrosUpdated, HttpStatus.OK);
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
