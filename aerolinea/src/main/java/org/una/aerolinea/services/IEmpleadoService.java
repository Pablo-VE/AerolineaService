/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.EmpleadoDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IEmpleadoService {
    public Optional<List<EmpleadoDTO>> findAll();

    public Optional<EmpleadoDTO> findById(Long id);
    
    public Optional<EmpleadoDTO> findByCedula(String cedula);
    
    public Optional<List<EmpleadoDTO>> findByCedulaContaining(String cedula);

    public Optional<List<EmpleadoDTO>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<EmpleadoDTO>> findByEstado(boolean estado);

    public EmpleadoDTO create(EmpleadoDTO empleado);

    public Optional<EmpleadoDTO> update(EmpleadoDTO empleado, Long id);
    
}
