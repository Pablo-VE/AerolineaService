/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.dto.RolDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IRolService {
    public Optional<List<RolDTO>> findAll();

    public Optional<RolDTO> findById(Long id);
    
    public Optional<List<RolDTO>> findByNombre(String nombre);

    public Optional<List<RolDTO>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<RolDTO>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<RolDTO>> findByEstado(boolean estado);

    public RolDTO create(RolDTO rol);

    public Optional<RolDTO> update(RolDTO rol, Long id);
}
