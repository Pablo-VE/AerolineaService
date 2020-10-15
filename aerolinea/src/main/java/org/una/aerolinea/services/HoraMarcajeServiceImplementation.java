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
import org.una.aerolinea.entities.HoraMarcaje;
import org.una.aerolinea.repositories.IHoraMarcajeRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class HoraMarcajeServiceImplementation implements IHoraMarcajeService{
    @Autowired
    private IHoraMarcajeRepository horaMarcajeRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcaje>> findAll() {
        return Optional.ofNullable(horaMarcajeRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HoraMarcaje> findById(Long id) {
        return horaMarcajeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcaje>> findByEmpleado(Long empleado) {
        return Optional.ofNullable(horaMarcajeRepository.findByEmpleado(empleado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcaje>> findByTipo(int tipo) {
        return Optional.ofNullable(horaMarcajeRepository.findByTipo(tipo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcaje>> findByEstado(boolean estado) {
         return Optional.ofNullable(horaMarcajeRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public HoraMarcaje create(HoraMarcaje horaMarcaje) {
        return horaMarcajeRepository.save(horaMarcaje);
    }

    @Override
    @Transactional
    public Optional<HoraMarcaje> update(HoraMarcaje horaMarcaje, Long id) {
        if(horaMarcajeRepository.findById(id).isPresent()){
            return Optional.ofNullable(horaMarcajeRepository.save(horaMarcaje));
        }else{
            return null;
        }
    }
    
}
