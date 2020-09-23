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
import org.una.aerolinea.entities.ServicioAeropuerto;
import org.una.aerolinea.repositories.IServicioAeropuertoRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class ServicioAeropuertoServiceImplementation implements IServicioAeropuertoService{
    @Autowired
    private IServicioAeropuertoRepository servicioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioAeropuerto>> findAll() {
        return Optional.ofNullable(servicioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioAeropuerto> findById(Long id) {
        return servicioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioAeropuerto>> findByCobroRango(float cobroMas, float cobroMenos) {
        return Optional.ofNullable(servicioRepository.findByCobroRango(cobroMas, cobroMenos));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioAeropuerto>> findByTipoContainingIgnoreCase(String tipo) {
        return Optional.ofNullable(servicioRepository.findByTipoContainingIgnoreCase(tipo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioAeropuerto>> findByEstadoCobro(boolean estadoCobro) {
        return Optional.ofNullable(servicioRepository.findByEstadoCobro(estadoCobro));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioAeropuerto>> findByEstado(boolean estado) {
        return Optional.ofNullable(servicioRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public ServicioAeropuerto create(ServicioAeropuerto servicioAeropuerto) {
        return servicioRepository.save(servicioAeropuerto);
    }

    @Override
    @Transactional
    public Optional<ServicioAeropuerto> update(ServicioAeropuerto servicioAeropuerto, Long id) {
        if(servicioRepository.findById(id).isPresent()){
            return Optional.ofNullable(servicioRepository.save(servicioAeropuerto));
        }else{
            return null;
        }
    }
    
}
