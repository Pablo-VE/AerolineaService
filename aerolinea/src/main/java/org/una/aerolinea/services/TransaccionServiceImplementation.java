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
import org.una.aerolinea.entities.Transaccion;
import org.una.aerolinea.repositories.ITransaccionRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TransaccionServiceImplementation implements ITransaccionService {
    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    public Optional<List<Transaccion>> findAll() {
        return Optional.ofNullable(transaccionRepository.findAll());
    }

    @Override
    public Optional<Transaccion> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    @Override
    public Optional<List<Transaccion>> findByLugarContainingIgnoreCase(String lugar) {
        return Optional.ofNullable(transaccionRepository.findByLugarContainingIgnoreCase(lugar));
    }

    @Override
    public Optional<List<Transaccion>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return Optional.ofNullable(transaccionRepository.findByDescripcionContainingIgnoreCase(descripcion));
    }

    @Override
    public Optional<List<Transaccion>> findByEstado(boolean estado) {
        return Optional.ofNullable(transaccionRepository.findByEstado(estado));
    }

    @Override
    public Transaccion create(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    public Optional<Transaccion> update(Transaccion transaccion, Long id) {
        if (transaccionRepository.findById(id).isPresent()) {
            return Optional.ofNullable(transaccionRepository.save(transaccion));
        } else {
            return null;
        }
    }
    
}
