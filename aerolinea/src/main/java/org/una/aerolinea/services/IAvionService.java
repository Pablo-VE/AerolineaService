/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Avion;

/**
 *
 * @author Pablo-VE
 */
public interface IAvionService {
    public Optional<List<Avion>> findAll();

    public Optional<Avion> findById(Long id);
    
    public Optional<Avion> findByMatricula(String matricula);

    public Optional<List<Avion>> findByMatriculaContainingIgnoreCase(String matricula);
    
    public Optional<List<Avion>> findByEstado(boolean estado);
    
    public Optional<List<Avion>> findByAerolinea(Long aerolinea);
    
    public Optional<List<Avion>> findByTipoAvion(Long tipoAvion);

    public Avion create(Avion avion);

    public Optional<Avion> update(Avion avion, Long id);
    
}
