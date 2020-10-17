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
import org.una.aerolinea.entities.AlertaGenerada;
import org.una.aerolinea.repositories.IAlertaGeneradaRepository;

/**
 *
 * @author Luis
 */
@Service
public class AlertaGeneradaServiceImplementation implements IAlertaGeneradaService{

    @Autowired
    private IAlertaGeneradaRepository alertaGeneradaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGenerada>> findAll() {
        return Optional.ofNullable(alertaGeneradaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertaGenerada> findById(Long id) {
        return alertaGeneradaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGenerada>> findByAutorizacion(String autorizacion) {
        return Optional.ofNullable(alertaGeneradaRepository.findByAutorizacion(autorizacion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGenerada>> findByEstado(boolean estado) {
        return Optional.ofNullable(alertaGeneradaRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGenerada>> findByTipoAlerta(Long tipoAlerta) {
        return Optional.ofNullable(alertaGeneradaRepository.findByTipoAlertaSQL(tipoAlerta));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGenerada>> findByVuelo(Long vuelo) {
        return Optional.ofNullable(alertaGeneradaRepository.findByVueloSQL(vuelo));
    }

    @Override
    @Transactional
    public AlertaGenerada create(AlertaGenerada alertaGenerada) {
        return alertaGeneradaRepository.save(alertaGenerada);
    }


    @Override
    @Transactional
    public Optional<AlertaGenerada> update(AlertaGenerada alertaGenerada, Long id) {
        if(alertaGeneradaRepository.findById(id).isPresent()){
            return Optional.ofNullable(alertaGeneradaRepository.save(alertaGenerada));
        }else{
            return null;
        }
    }
    
    
    
}
