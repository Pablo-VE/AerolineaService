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
import org.una.aerolinea.entities.TipoServicioAeropuerto;
import org.una.aerolinea.repositories.ITipoServicioAeropuertoRepository;

/**
 *
 * @author Luis
 */
@Service
public class TipoServicioAeropuertoImplementation implements ITipoServicioAeropuertoService{
   
    @Autowired
    private ITipoServicioAeropuertoRepository tipoServicioAeroRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicioAeropuerto>> findAll() {
        return Optional.ofNullable(tipoServicioAeroRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicioAeropuerto> findById(Long id) {
        return tipoServicioAeroRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicioAeropuerto>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(tipoServicioAeroRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicioAeropuerto>> findByEstado(boolean estado) {
        return Optional.ofNullable(tipoServicioAeroRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicioAeropuerto>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(tipoServicioAeroRepository.findByDescripcion(descripcion));
    }

    @Override
    @Transactional
    public TipoServicioAeropuerto create(TipoServicioAeropuerto tipoServicioAeropuerto) {
        return tipoServicioAeroRepository.save(tipoServicioAeropuerto);
    }

    @Override
    @Transactional
    public Optional<TipoServicioAeropuerto> update(TipoServicioAeropuerto tipoServicioAeropuerto, Long id) {
        if(tipoServicioAeroRepository.findById(id).isPresent()){
            return Optional.ofNullable(tipoServicioAeroRepository.save(tipoServicioAeropuerto));
        }else{
            return null;
        }
    }
}
