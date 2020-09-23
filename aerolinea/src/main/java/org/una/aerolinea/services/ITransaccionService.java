/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Transaccion;

/**
 *
 * @author Pablo-VE
 */
public interface ITransaccionService {
    public Optional<List<Transaccion>> findAll();

    public Optional<Transaccion> findById(Long id);

    public Optional<List<Transaccion>> findByLugarContainingIgnoreCase(String lugar);
    
    public Optional<List<Transaccion>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<Transaccion>> findByEstado(boolean estado);
    
    public Optional<List<Transaccion>> findByRol(Long rol);

    public Transaccion create(Transaccion transaccion);

    public Optional<Transaccion> update(Transaccion transaccion, Long id);
    
}
