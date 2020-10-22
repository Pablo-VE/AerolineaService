/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.AreaTrabajoDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IAreaTrabajoService {
    public Optional<List<AreaTrabajoDTO>> findAll();

    public Optional<AreaTrabajoDTO> findById(Long id);

    public Optional<List<AreaTrabajoDTO>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<AreaTrabajoDTO>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<AreaTrabajoDTO>> findByEstado(boolean estado);

    public AreaTrabajoDTO create(AreaTrabajoDTO areaTrabajo);

    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO areaTrabajo, Long id);
    
}
