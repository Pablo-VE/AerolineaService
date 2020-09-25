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
import org.springframework.transaction.annotation.Transactional;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.repositories.IRolRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class RolServiceImplementation implements IRolService {
    @Autowired
    private IRolRepository rolRepositoy;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Rol>> findAll() {
        return Optional.ofNullable(rolRepositoy.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findById(Long id) {
        return rolRepositoy.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findByNombre(String nombre) {
        return Optional.ofNullable(rolRepositoy.findByNombre(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Rol>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(rolRepositoy.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Rol>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return Optional.ofNullable(rolRepositoy.findByDescripcionContainingIgnoreCase(descripcion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Rol>> findByEstado(boolean estado) {
        return Optional.ofNullable(rolRepositoy.findByEstado(estado));
    }

    @Override
    @Transactional
    public Rol create(Rol rol) {
        return rolRepositoy.save(rol);
    }

    @Override
    @Transactional
    public Optional<Rol> update(Rol rol, Long id) {
        if (rolRepositoy.findById(id).isPresent()) {
            
            return Optional.ofNullable(rolRepositoy.save(rol));
        } else {
            return null;
        }
    }
    
}
