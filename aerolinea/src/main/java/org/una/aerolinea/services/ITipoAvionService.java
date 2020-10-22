/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.TipoAvionDTO;

/**
 *
 * @author Pablo-VE
 */
public interface ITipoAvionService {
    public Optional<List<TipoAvionDTO>> findAll();

    public Optional<TipoAvionDTO> findById(Long id);

    public Optional<List<TipoAvionDTO>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<TipoAvionDTO>> findByEstado(boolean estado);
    
    public Optional<List<TipoAvionDTO>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima);

    public TipoAvionDTO create(TipoAvionDTO tipoAvion);

    public Optional<TipoAvionDTO> update(TipoAvionDTO tipoAvion, Long id);
    
}
