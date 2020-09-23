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
import org.una.aerolinea.entities.TipoAvion;
import org.una.aerolinea.repositories.ITipoAvionRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TipoAvionServiceImplementation implements ITipoAvionService{
    @Autowired
    private ITipoAvionRepository tipoAvionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvion>> findAll() {
        return Optional.ofNullable(tipoAvionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAvion> findById(Long id) {
        return tipoAvionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvion>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(tipoAvionRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvion>> findByEstado(boolean estado) {
        return Optional.ofNullable(tipoAvionRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvion>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima) {
        return Optional.ofNullable(tipoAvionRepository.findByDistanciaRango(distanciaMaxima, distanciaMinima));
    }

    @Override
    @Transactional
    public TipoAvion create(TipoAvion tipoAvion) {
        return tipoAvionRepository.save(tipoAvion);
    }

    @Override
    @Transactional
    public Optional<TipoAvion> update(TipoAvion tipoAvion, Long id) {
        if(tipoAvionRepository.findById(id).isPresent()){
            return Optional.ofNullable(tipoAvionRepository.save(tipoAvion));
        }else{
            return null;
        }
    }

    
}
