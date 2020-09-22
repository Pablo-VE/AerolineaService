/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.ParametroAplicacion;

/**
 *
 * @author Pablo-VE
 */
public interface IParametroAplicacionService {
    public Optional<List<ParametroAplicacion>> findAll();

    public Optional<ParametroAplicacion> findById(Long id);

    public Optional<List<ParametroAplicacion>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<ParametroAplicacion>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<ParametroAplicacion>> findByEstado(boolean estado);

    public ParametroAplicacion create(ParametroAplicacion parametroAplicacion);

    public Optional<ParametroAplicacion> update(ParametroAplicacion parametroAplicacion, Long id);
    
}
