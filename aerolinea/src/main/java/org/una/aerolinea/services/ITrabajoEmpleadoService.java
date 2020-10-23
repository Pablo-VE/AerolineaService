/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.TrabajoEmpleadoDTO;

/**
 *
 * @author Pablo-VE
 */
public interface ITrabajoEmpleadoService {
    public Optional<List<TrabajoEmpleadoDTO>> findAll();
    
    public Optional<List<TrabajoEmpleadoDTO>> findByEstado(boolean estado);
    
    public Optional<List<TrabajoEmpleadoDTO>> findByEmpleado(Long empleado);
    
    public Optional<List<TrabajoEmpleadoDTO>> findByAreaTrabajo(Long areaTrabajo);

    public Optional<TrabajoEmpleadoDTO> findById(Long id);

    public TrabajoEmpleadoDTO create(TrabajoEmpleadoDTO trabajoEmpleado);

    public Optional<TrabajoEmpleadoDTO> update(TrabajoEmpleadoDTO trabajoEmpleado, Long id);
    
}
