/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.BitacoraAvionDTO;

/**
 *
 * @author Luis
 */
public interface IBitacoraAvionService {
    
    public Optional<List<BitacoraAvionDTO>> findAll();
 
    public Optional<BitacoraAvionDTO> findById(Long id);

    public Optional<List<BitacoraAvionDTO>> findByCombustible(int combustible);
    
    public Optional<List<BitacoraAvionDTO>> findByDistanciaRecorrida(int distanciaRec);
    
    public Optional<List<BitacoraAvionDTO>> findByEstado(boolean estado);
    
    public Optional<List<BitacoraAvionDTO>> findByTiempoTierra(int tiempoTierra);
    
    public Optional<List<BitacoraAvionDTO>> findByUbicacionContainingIgnoreCase(String ubicacion);

    public BitacoraAvionDTO create(BitacoraAvionDTO avionEstado);

    public Optional<BitacoraAvionDTO> update(BitacoraAvionDTO avionEstado, Long id);

}
