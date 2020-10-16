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
import org.una.aerolinea.entities.AvionEstado;
import org.una.aerolinea.repositories.IAvionEstadoRepository;
 

/**
 *
 * @author Luis
 */
@Service
public class AvionEstadoServiceImplementation implements IAvionEstadoService{

    @Autowired
    private IAvionEstadoRepository avionEstadoRepository;
    
    @Override
    public Optional<List<AvionEstado>> findAll() {
        return Optional.ofNullable(avionEstadoRepository.findAll());
    }

    @Override
    public Optional<AvionEstado> findById(Long id) {
        return avionEstadoRepository.findById(id);
    }

    @Override
    public Optional<List<AvionEstado>> findByCombustible(int combustible) {
        return Optional.ofNullable(avionEstadoRepository.findByCombustible(combustible));
    }

    @Override
    public Optional<List<AvionEstado>> findByDistanciaRecorrida(int distanciaRec) {
        return Optional.ofNullable(avionEstadoRepository.findByDistanciaRecorrida(distanciaRec));
    }

    @Override
    public Optional<List<AvionEstado>> findByEstado(boolean estado) {
        return Optional.ofNullable(avionEstadoRepository.findByEstado(estado));
    }

    @Override
    public Optional<List<AvionEstado>> findByTiempoTierra(int tiempoTierra) {
        return Optional.ofNullable(avionEstadoRepository.findByTiempoTierra(tiempoTierra));
    }

    @Override
    public Optional<List<AvionEstado>> findByUbicacionContainingIgnoreCase(String ubicacion) {
        return Optional.ofNullable(avionEstadoRepository.findByUbicacionContainingIgnoreCase(ubicacion));
    }

    @Override
    public AvionEstado create(AvionEstado avionEstado) {
        return avionEstadoRepository.save(avionEstado);
    }

    @Override
    public Optional<AvionEstado> update(AvionEstado avionEstado, Long id) {
        if(avionEstadoRepository.findById(id).isPresent()){
            return Optional.ofNullable(avionEstadoRepository.save(avionEstado));
        }else{
            return null;
        }
    }
    
}
