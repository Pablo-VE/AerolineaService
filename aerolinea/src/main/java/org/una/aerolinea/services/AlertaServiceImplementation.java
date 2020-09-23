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
import org.una.aerolinea.entities.Alerta;
import org.una.aerolinea.repositories.IAlertaRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class AlertaServiceImplementation implements IAlertaService{
    @Autowired 
    private IAlertaRepository alertaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Alerta>> findAll() {
        return Optional.ofNullable(alertaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alerta> findById(Long id) {
        return alertaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Alerta>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return Optional.ofNullable(alertaRepository.findByDescripcionContainingIgnoreCase(descripcion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Alerta>> findByEstado(boolean estado) {
        return Optional.ofNullable(alertaRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alerta> findByVuelo(Long vuelo) {
        return Optional.ofNullable(alertaRepository.findByVuelo(vuelo));
    }

    @Override
    @Transactional
    public Alerta create(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    @Override
    @Transactional
    public Optional<Alerta> update(Alerta alerta, Long id) {
        if(alertaRepository.findById(id).isPresent()){
            return Optional.ofNullable(alertaRepository.save(alerta));
        }else{
            return null;
        }
    }
    
}
