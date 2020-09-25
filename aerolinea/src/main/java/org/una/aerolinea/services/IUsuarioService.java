/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.AuthenticationRequest;
import org.una.aerolinea.dto.AuthenticationResponse;
import org.una.aerolinea.entities.Usuario;

/**
 *
 * @author Pablo-VE
 */
public interface IUsuarioService {
    public Optional<List<Usuario>> findAll();
    
    public Optional<List<Usuario>> findByEstado(boolean estado);
    
    public Optional<Usuario> findByCedula(String cedula);
    
    public Optional<List<Usuario>> findByRol(Long rol);

    public Optional<Usuario> findById(Long id);

    public Usuario create(Usuario usuario);

    public Optional<Usuario> update(Usuario usuario, Long id);
    
    
}
