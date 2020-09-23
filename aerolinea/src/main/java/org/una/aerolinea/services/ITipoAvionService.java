/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.TipoAvion;

/**
 *
 * @author Pablo-VE
 */
public interface ITipoAvionService {
    public Optional<List<TipoAvion>> findAll();

    public Optional<TipoAvion> findById(Long id);

    public Optional<List<TipoAvion>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<TipoAvion>> findByEstado(boolean estado);
    
    public Optional<List<TipoAvion>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima);

    public TipoAvion create(TipoAvion tipoAvion);

    public Optional<TipoAvion> update(TipoAvion tipoAvion, Long id);
    
}
