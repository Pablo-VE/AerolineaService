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
import org.una.aerolinea.entities.ParametroAplicacion;
import org.una.aerolinea.repositories.IParametroAplicacionRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class ParametroAplicacionServiceImplementation implements IParametroAplicacionService{
    @Autowired
    private IParametroAplicacionRepository paramentroRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacion>> findAll() {
        return Optional.ofNullable(paramentroRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroAplicacion> findById(Long id) {
        return paramentroRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacion>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(paramentroRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacion>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return Optional.ofNullable(paramentroRepository.findByDescripcionContainingIgnoreCase(descripcion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacion>> findByEstado(boolean estado) {
        return Optional.ofNullable(paramentroRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public ParametroAplicacion create(ParametroAplicacion parametroAplicacion) {
        return paramentroRepository.save(parametroAplicacion);
    }

    @Override
    @Transactional
    public Optional<ParametroAplicacion> update(ParametroAplicacion parametroAplicacion, Long id) {
        if (paramentroRepository.findById(id).isPresent()) {
            
            return Optional.ofNullable(paramentroRepository.save(parametroAplicacion));
        } else {
            return null;
        }
    }
    
}
