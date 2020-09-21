/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Empleado;

/**
 *
 * @author Pablo-VE
 */
public interface IEmpleadoService {
    public Optional<List<Empleado>> findAll();

    public Optional<Empleado> findById(Long id);
    
    public Optional<Empleado> findByCedula(String cedula);
    
    public Optional<List<Empleado>> findByCedulaContaining(String cedula);

    public Optional<List<Empleado>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<Empleado>> findByEstado(boolean estado);

    public Empleado create(Empleado empleado);

    public Optional<Empleado> update(Empleado empleado, Long id);
    
}
