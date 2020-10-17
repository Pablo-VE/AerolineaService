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
import org.una.aerolinea.entities.BitacoraAvion;
import org.una.aerolinea.repositories.IBitacoraAvionRepository;

import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author Luis
 */
@Service
public class BitacoraAvionServiceImplementation implements IBitacoraAvionService{

    @Autowired
    private IBitacoraAvionRepository avionEstadoRepository;
    
    @Override
    public Optional<List<BitacoraAvion>> findAll() {
		return Optional.ofNullable(avionEstadoRepository.findAll());
    }

    @Override
    public Optional<BitacoraAvion> findById(Long id) {
		return avionEstadoRepository.findById(id);
    }

    @Override
    public Optional<List<BitacoraAvion>> findByCombustible(int combustible) {
		return Optional.ofNullable(avionEstadoRepository.findByCombustible(combustible));
    }

    @Override
    public Optional<List<BitacoraAvion>> findByDistanciaRecorrida(int distanciaRec) {
		return Optional.ofNullable(avionEstadoRepository.findByDistanciaRecorrida(distanciaRec));
    }

    @Override
    public Optional<List<BitacoraAvion>> findByEstado(boolean estado) {
		return Optional.ofNullable(avionEstadoRepository.findByEstado(estado));
    }

    @Override
    public Optional<List<BitacoraAvion>> findByTiempoTierra(int tiempoTierra) {
		return Optional.ofNullable(avionEstadoRepository.findByTiempoTierra(tiempoTierra));
    }

    @Override
    public Optional<List<BitacoraAvion>> findByUbicacionContainingIgnoreCase(String ubicacion) {
		return Optional.ofNullable(avionEstadoRepository.findByUbicacionContainingIgnoreCase(ubicacion));
    }

    @Override
    public BitacoraAvion create(BitacoraAvion avionEstado) {
		return avionEstadoRepository.save(avionEstado);
    }

    @Override
    public Optional<BitacoraAvion> update(BitacoraAvion avionEstado, Long id) {
		if(avionEstadoRepository.findById(id).isPresent()){
            return Optional.ofNullable(avionEstadoRepository.save(avionEstado));
        }else{
            return null;
        }
    }
    
}
