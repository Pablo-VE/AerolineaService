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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.una.aerolinea.dto.AuthenticationRequest;
import org.una.aerolinea.dto.AuthenticationResponse;
import org.una.aerolinea.dto.UsuarioDTO;
import org.una.aerolinea.entities.Usuario;
import org.una.aerolinea.services.IUsuarioService;
import org.una.aerolinea.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/usuarios")  
@Api(tags = {"Usuarios"})
public class UsuarioController {
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Usuario>> resultadoFound = usuarioService.findAll();
            if (resultadoFound.isPresent()) {
                List<UsuarioDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un usuario por su id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Usuario> resultadoFound = usuarioService.findById(id);
            if (resultadoFound.isPresent()) {
                UsuarioDTO resultadoDto = MapperUtils.DtoFromEntity(resultadoFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/estado/{term}") 
    @ApiOperation(value = "Obtiene una lista de usuarios por estado", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Usuario>> resultadoFound = usuarioService.findByEstado(term);
            if (resultadoFound.isPresent()) {
                List<UsuarioDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/list/rol/{term}") 
    @ApiOperation(value = "Obtiene una lista de usuarios por su rol", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByRol(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Usuario>> resultadoFound = usuarioService.findByRol(term);
            if (resultadoFound.isPresent()) {
                List<UsuarioDTO> resultadoDTO = MapperUtils.DtoListFromEntityList(resultadoFound.get(), UsuarioDTO.class);
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
    @ApiOperation(value = "Crea un usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            Usuario entityCreated = usuarioService.create(usuario);
            UsuarioDTO resultDto = MapperUtils.DtoFromEntity(entityCreated, UsuarioDTO.class);
            return new ResponseEntity<>(resultDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PutMapping("/modificar/{id}") 
    @ApiOperation(value = "Modifica un usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Usuario entityModified) {
        try {
            Optional<Usuario> entityUpdated = usuarioService.update(entityModified, id);
            if (entityUpdated.isPresent()) {
                UsuarioDTO resultDto = MapperUtils.DtoFromEntity(entityUpdated.get(), UsuarioDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
   
    
}
