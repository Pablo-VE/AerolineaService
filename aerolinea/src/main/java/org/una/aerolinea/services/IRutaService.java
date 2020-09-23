/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Ruta;

/**
 *
 * @author Pablo-VE
 */
public interface IRutaService {
    public Optional<List<Ruta>> findAll();

    public Optional<Ruta> findById(Long id);
    
    public Optional<List<Ruta>> findByOrigenContainingIgnoreCase(String origen);
    
    public Optional<List<Ruta>> findByDestinoContainingIgnoreCase(String destino);
    
    public Optional<List<Ruta>> findByEstado(boolean estado);
    
    public Optional<List<Ruta>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima);

    public Ruta create(Ruta ruta);

    public Optional<Ruta> update(Ruta ruta, Long id);
    
}
