/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.RutaDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IRutaService {
    public Optional<List<RutaDTO>> findAll();

    public Optional<RutaDTO> findById(Long id);
    
    public Optional<List<RutaDTO>> findByOrigenContainingIgnoreCase(String origen);
    
    public Optional<List<RutaDTO>> findByDestinoContainingIgnoreCase(String destino);
    
    public Optional<List<RutaDTO>> findByEstado(boolean estado);
    
    public Optional<List<RutaDTO>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima);

    public RutaDTO create(RutaDTO ruta);

    public Optional<RutaDTO> update(RutaDTO ruta, Long id);
    
}
