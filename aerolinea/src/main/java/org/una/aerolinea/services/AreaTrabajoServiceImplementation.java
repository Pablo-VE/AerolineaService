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
import org.una.aerolinea.entities.AreaTrabajo;
import org.una.aerolinea.repositories.IAreaTrabajoRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {
    @Autowired
    private IAreaTrabajoRepository atrabajoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajo>> findAll() {
        return Optional.ofNullable(atrabajoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajo> findById(Long id) {
        return atrabajoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajo>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(atrabajoRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajo>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return Optional.ofNullable(atrabajoRepository.findByDescripcionContainingIgnoreCase(descripcion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajo>> findByEstado(boolean estado) {
        return Optional.ofNullable(atrabajoRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public AreaTrabajo create(AreaTrabajo areaTrabajo) {
        return atrabajoRepository.save(areaTrabajo);
    }

    @Override
    @Transactional
    public Optional<AreaTrabajo> update(AreaTrabajo areaTrabajo, Long id) {
        if (atrabajoRepository.findById(id).isPresent()) {
            
            return Optional.ofNullable(atrabajoRepository.save(areaTrabajo));
        } else {
            return null;
        }
    }
}
