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
import org.una.aerolinea.entities.ServicioTipo;
import org.una.aerolinea.repositories.IServicioTipoRepository;

/**
 *
 * @author Luis
 */
@Service
public class ServicioTipoImplementation implements IServicioTipoService{
   
    @Autowired
    private IServicioTipoRepository tipoServicioAeroRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipo>> findAll() {
        return Optional.ofNullable(tipoServicioAeroRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioTipo> findById(Long id) {
        return tipoServicioAeroRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipo>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(tipoServicioAeroRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipo>> findByEstado(boolean estado) {
        return Optional.ofNullable(tipoServicioAeroRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipo>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(tipoServicioAeroRepository.findByDescripcion(descripcion));
    }

    @Override
    @Transactional(readOnly = true)
    public ServicioTipo create(ServicioTipo tipoServicioAeropuerto) {
		return tipoServicioAeroRepository.save(tipoServicioAeropuerto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioTipo> update(ServicioTipo tipoServicioAeropuerto, Long id) {
		if(tipoServicioAeroRepository.findById(id).isPresent()){
            return Optional.ofNullable(tipoServicioAeroRepository.save(tipoServicioAeropuerto));
        }else{
            return null;
        }
    }
}
