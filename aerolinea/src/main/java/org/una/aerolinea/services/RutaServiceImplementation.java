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
import org.una.aerolinea.entities.Ruta;
import org.una.aerolinea.repositories.IRutaRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class RutaServiceImplementation implements IRutaService{
    @Autowired
    private IRutaRepository rutaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Ruta>> findAll() {
        return Optional.ofNullable(rutaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ruta> findById(Long id) {
         return rutaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Ruta>> findByOrigenContainingIgnoreCase(String origen) {
        return Optional.ofNullable(rutaRepository.findByOrigenContainingIgnoreCase(origen));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Ruta>> findByDestinoContainingIgnoreCase(String destino) {
        return Optional.ofNullable(rutaRepository.findByDestinoContainingIgnoreCase(destino));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Ruta>> findByEstado(boolean estado) {
        return Optional.ofNullable(rutaRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Ruta>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima) {
        return Optional.ofNullable(rutaRepository.findByDistanciaRango(distanciaMaxima, distanciaMinima));
    }

    @Override
    @Transactional
    public Ruta create(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Override
    @Transactional
    public Optional<Ruta> update(Ruta ruta, Long id) {
        if(rutaRepository.findById(id).isPresent()){
            return Optional.ofNullable(rutaRepository.save(ruta));
        }else{
            return null;
        }
    }

    
    
}
