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
import org.una.aerolinea.entities.Horario;
import org.una.aerolinea.repositories.IHorarioRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class HorarioServiceImplementation implements IHorarioService{
    @Autowired
    private IHorarioRepository horarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Horario>> findAll() {
        return Optional.ofNullable(horarioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Horario>> findByEstado(boolean estado) {
        return Optional.ofNullable(horarioRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Horario>> findByEmpleado(Long empleado) {
        return Optional.ofNullable(horarioRepository.findByEmpleado(empleado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Horario>> findByEmpleadoAndEstado(Long empleado, boolean estado) {
        return Optional.ofNullable(horarioRepository.findByEmpleadoAndEstado(empleado, estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Horario> findById(Long id) {
        return horarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Horario create(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    @Transactional
    public Optional<Horario> update(Horario horario, Long id) {
        if(horarioRepository.findById(id).isPresent()){
            return Optional.ofNullable(horarioRepository.save(horario));
        }else{
            return null;
        }
    }
    
}
