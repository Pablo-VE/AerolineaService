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
import org.una.aerolinea.entities.Avion;
import org.una.aerolinea.repositories.IAvionRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class AvionServiceImplementation implements IAvionService{
    @Autowired
    private IAvionRepository avionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Avion>> findAll() {
        return Optional.ofNullable(avionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Avion> findById(Long id) {
        return avionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Avion> findByMatricula(String matricula) {
        return Optional.ofNullable(avionRepository.findByMatricula(matricula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Avion>> findByMatriculaContainingIgnoreCase(String matricula) {
        return Optional.ofNullable(avionRepository.findByMatriculaContainingIgnoreCase(matricula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Avion>> findByEstado(boolean estado) {
        return Optional.ofNullable(avionRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Avion>> findByAerolinea(Long aerolinea) {
        return Optional.ofNullable(avionRepository.findByAerolinea(aerolinea));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Avion>> findByTipoAvion(Long tipoAvion) {
        return Optional.ofNullable(avionRepository.findByTipoAvion(tipoAvion));
    }

    @Override
    @Transactional
    public Avion create(Avion avion) {
        return avionRepository.save(avion);
    }

    @Override
    @Transactional
    public Optional<Avion> update(Avion avion, Long id) {
        if(avionRepository.findById(id).isPresent()){
            return Optional.ofNullable(avionRepository.save(avion));
        }else{
            return null;
        }
    }

    
    
}
