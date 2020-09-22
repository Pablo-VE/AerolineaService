/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.TrabajoEmpleado;

/**
 *
 * @author Pablo-VE
 */
public interface ITrabajoEmpleadoService {
    public Optional<List<TrabajoEmpleado>> findAll();
    
    public Optional<List<TrabajoEmpleado>> findByEstado(boolean estado);
    
    public Optional<List<TrabajoEmpleado>> findByEmpleado(Long empleado);
    
    public Optional<List<TrabajoEmpleado>> findByAreaTrabajo(Long areaTrabajo);

    public Optional<TrabajoEmpleado> findById(Long id);

    public TrabajoEmpleado create(TrabajoEmpleado trabajoEmpleado);

    public Optional<TrabajoEmpleado> update(TrabajoEmpleado trabajoEmpleado, Long id);
    
}
