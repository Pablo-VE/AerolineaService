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
import org.una.aerolinea.entities.Aerolinea;
import org.una.aerolinea.repositories.IAerolineaRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class AerolineaServiceImplementation implements IAerolineaService {
    @Autowired
    private IAerolineaRepository aerolineaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Aerolinea>> findAll() {
        return Optional.ofNullable(aerolineaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Aerolinea> findById(Long id) {
        return aerolineaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Aerolinea>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(aerolineaRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Aerolinea>> findByResponsableContainingIgnoreCase(String responsable) {
        return Optional.ofNullable(aerolineaRepository.findByResponsableContainingIgnoreCase(responsable));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Aerolinea>> findByEstado(boolean estado) {
        return Optional.ofNullable(aerolineaRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public Aerolinea create(Aerolinea aerolinea) {
        return aerolineaRepository.save(aerolinea);
    }

    @Override
    @Transactional
    public Optional<Aerolinea> update(Aerolinea aerolinea, Long id) {
        if(aerolineaRepository.findById(id).isPresent()){
            return Optional.ofNullable(aerolineaRepository.save(aerolinea));
        }else{
            return null;
        }
    }
    
}
