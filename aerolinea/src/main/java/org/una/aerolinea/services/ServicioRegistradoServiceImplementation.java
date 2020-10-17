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
import org.una.aerolinea.entities.ServicioRegistrado;
import org.una.aerolinea.repositories.IServicioRegistradoRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class ServicioRegistradoServiceImplementation implements IServicioRegistradoService{
    @Autowired
    private IServicioRegistradoRepository servicioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistrado>> findAll() {
        return Optional.ofNullable(servicioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioRegistrado> findById(Long id) {
        return servicioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistrado>> findByCobroRango(float cobroMas, float cobroMenos) {
        return Optional.ofNullable(servicioRepository.findByCobroRango(cobroMas, cobroMenos));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistrado>> findByTipoContainingIgnoreCase(String tipo) {
        return Optional.ofNullable(servicioRepository.findByTipoContainingIgnoreCase(tipo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistrado>> findByEstadoCobro(boolean estadoCobro) {
        return Optional.ofNullable(servicioRepository.findByEstadoCobro(estadoCobro));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistrado>> findByEstado(boolean estado) {
        return Optional.ofNullable(servicioRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public ServicioRegistrado create(ServicioRegistrado servicioAeropuerto) {
        return servicioRepository.save(servicioAeropuerto);
    }

    @Override
    @Transactional
    public Optional<ServicioRegistrado> update(ServicioRegistrado servicioAeropuerto, Long id) {
        if(servicioRepository.findById(id).isPresent()){
            return Optional.ofNullable(servicioRepository.save(servicioAeropuerto));
        }else{
            return null;
        }
    }
    
}
