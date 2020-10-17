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
    private IBitacoraAvionRepository bitacoraAvionRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvion>> findAll() {
	return Optional.ofNullable(bitacoraAvionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BitacoraAvion> findById(Long id) {
	return bitacoraAvionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvion>> findByCombustible(int combustible) {
	return Optional.ofNullable(bitacoraAvionRepository.findByCombustible(combustible));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvion>> findByDistanciaRecorrida(int distanciaRec) {
	return Optional.ofNullable(bitacoraAvionRepository.findByDistanciaRecorrida(distanciaRec));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvion>> findByEstado(boolean estado) {
	return Optional.ofNullable(bitacoraAvionRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvion>> findByTiempoTierra(int tiempoTierra) {
	return Optional.ofNullable(bitacoraAvionRepository.findByTiempoTierra(tiempoTierra));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvion>> findByUbicacionContainingIgnoreCase(String ubicacion) {
	return Optional.ofNullable(bitacoraAvionRepository.findByUbicacionContainingIgnoreCase(ubicacion));
    }

    @Override
    @Transactional
    public BitacoraAvion create(BitacoraAvion avionEstado) {
	return bitacoraAvionRepository.save(avionEstado);
    }

    @Override
    @Transactional
    public Optional<BitacoraAvion> update(BitacoraAvion avionEstado, Long id) {
	if(bitacoraAvionRepository.findById(id).isPresent()){
            return Optional.ofNullable(bitacoraAvionRepository.save(avionEstado));
        }else{
            return null;
        }
    }
    
}
