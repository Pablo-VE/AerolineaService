/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.AvionEstado;

/**
 *
 * @author Luis
 */
public interface IAvionEstadoService {
    
    public Optional<List<AvionEstado>> findAll();
 
    public Optional<AvionEstado> findById(Long id);

    public Optional<List<AvionEstado>> findByCombustible(int combustible);
    
    public Optional<List<AvionEstado>> findByDistanciaRecorrida(int distanciaRec);
    
    public Optional<List<AvionEstado>> findByEstado(boolean estado);
    
    public Optional<List<AvionEstado>> findByTiempoTierra(int tiempoTierra);
    
    public Optional<List<AvionEstado>> findByUbicacionContainingIgnoreCase(String ubicacion);

    public AvionEstado create(AvionEstado avionEstado);

    public Optional<AvionEstado> update(AvionEstado avionEstado, Long id);

}
