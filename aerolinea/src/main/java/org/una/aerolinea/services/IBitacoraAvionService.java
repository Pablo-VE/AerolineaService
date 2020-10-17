/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.BitacoraAvion;

/**
 *
 * @author Luis
 */
public interface IBitacoraAvionService {
    
    public Optional<List<BitacoraAvion>> findAll();
 
    public Optional<BitacoraAvion> findById(Long id);

    public Optional<List<BitacoraAvion>> findByCombustible(int combustible);
    
    public Optional<List<BitacoraAvion>> findByDistanciaRecorrida(int distanciaRec);
    
    public Optional<List<BitacoraAvion>> findByEstado(boolean estado);
    
    public Optional<List<BitacoraAvion>> findByTiempoTierra(int tiempoTierra);
    
    public Optional<List<BitacoraAvion>> findByUbicacionContainingIgnoreCase(String ubicacion);

    public BitacoraAvion create(BitacoraAvion avionEstado);

    public Optional<BitacoraAvion> update(BitacoraAvion avionEstado, Long id);

}
