/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Aerolinea;

/**
 *
 * @author Pablo-VE
 */
public interface IAerolineaService {
    public Optional<List<Aerolinea>> findAll();

    public Optional<Aerolinea> findById(Long id);

    public Optional<List<Aerolinea>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<Aerolinea>> findByResponsableContainingIgnoreCase(String responsable);
    
    public Optional<List<Aerolinea>> findByEstado(boolean estado);

    public Aerolinea create(Aerolinea aerolinea);

    public Optional<Aerolinea> update(Aerolinea aerolinea, Long id);
    
}
