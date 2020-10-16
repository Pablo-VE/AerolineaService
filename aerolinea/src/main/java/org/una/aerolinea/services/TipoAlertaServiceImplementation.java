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
import org.una.aerolinea.entities.TipoAlerta;
import org.una.aerolinea.repositories.ITipoAlertaRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TipoAlertaServiceImplementation implements ITipoAlertaService{
    @Autowired 
    private ITipoAlertaRepository alertaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAlerta>> findAll() {
        return Optional.ofNullable(alertaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAlerta> findById(Long id) {
        return alertaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAlerta>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return Optional.ofNullable(alertaRepository.findByDescripcionContainingIgnoreCase(descripcion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAlerta>> findByEstado(boolean estado) {
        return Optional.ofNullable(alertaRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public TipoAlerta create(TipoAlerta alerta) {
        return alertaRepository.save(alerta);
    }

    @Override
    @Transactional
    public Optional<TipoAlerta> update(TipoAlerta alerta, Long id) {
        if(alertaRepository.findById(id).isPresent()){
            return Optional.ofNullable(alertaRepository.save(alerta));
        }else{
            return null;
        }
    }
    
}
