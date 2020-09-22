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
import org.una.aerolinea.entities.TrabajoEmpleado;
import org.una.aerolinea.repositories.ITrabajoEmpleadoRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TrabajoEmpleadoServiceImplementation implements ITrabajoEmpleadoService{
    @Autowired
    ITrabajoEmpleadoRepository trabajoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleado>> findAll() {
        return Optional.ofNullable(trabajoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleado>> findByEstado(boolean estado) {
        return Optional.ofNullable(trabajoRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleado>> findByEmpleado(Long empleado) {
        return Optional.ofNullable(trabajoRepository.findByEmpleado(empleado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleado>> findByAreaTrabajo(Long areaTrabajo) {
        return Optional.ofNullable(trabajoRepository.findByAreaTrabajo(areaTrabajo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TrabajoEmpleado> findById(Long id) {
        return trabajoRepository.findById(id);
    }

    @Override
    @Transactional
    public TrabajoEmpleado create(TrabajoEmpleado trabajoEmpleado) {
        return trabajoRepository.save(trabajoEmpleado);
    }

    @Override
    @Transactional
    public Optional<TrabajoEmpleado> update(TrabajoEmpleado trabajoEmpleado, Long id) {
        if (trabajoRepository.findById(id).isPresent()) {
            
            return Optional.ofNullable(trabajoRepository.save(trabajoEmpleado));
        } else {
            return null;
        }
    }
    
    
}
