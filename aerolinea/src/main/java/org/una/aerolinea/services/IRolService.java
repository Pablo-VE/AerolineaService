/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Rol;

/**
 *
 * @author Pablo-VE
 */
public interface IRolService {
    public Optional<List<Rol>> findAll();

    public Optional<Rol> findById(Long id);
    
    public Optional<Rol> findByNombre(String nombre);

    public Optional<List<Rol>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<Rol>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<Rol>> findByEstado(boolean estado);

    public Rol create(Rol rol);

    public Optional<Rol> update(Rol rol, Long id);
}
