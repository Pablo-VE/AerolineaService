/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.AreaTrabajo;

/**
 *
 * @author Pablo-VE
 */
public interface IAreaTrabajoService {
    public Optional<List<AreaTrabajo>> findAll();

    public Optional<AreaTrabajo> findById(Long id);

    public Optional<List<AreaTrabajo>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<AreaTrabajo>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<AreaTrabajo>> findByEstado(boolean estado);

    public AreaTrabajo create(AreaTrabajo areaTrabajo);

    public Optional<AreaTrabajo> update(AreaTrabajo areaTrabajo, Long id);
    
}
