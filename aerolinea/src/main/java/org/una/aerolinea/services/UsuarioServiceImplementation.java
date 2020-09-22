/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aerolinea.entities.Usuario;
import org.una.aerolinea.repositories.IUsuarioRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class UsuarioServiceImplementation implements IUsuarioService{
    
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Optional<List<Usuario>> findAll() {
        return Optional.ofNullable(usuarioRepository.findAll());
    }

    @Override
    public Optional<List<Usuario>> findByEstado(boolean estado) {
        return Optional.ofNullable(usuarioRepository.findByEstado(estado));
    }

    @Override
    public Optional<List<Usuario>> findByRol(Long rol) {
        return Optional.ofNullable(usuarioRepository.findByRol(rol));
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            
            return Optional.ofNullable(usuarioRepository.save(usuario));
        } else {
            return null;
        }
    }
    
}
