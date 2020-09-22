/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aerolinea.entities.Empleado;
import org.una.aerolinea.repositories.IEmpleadoRepository;

/**
 *
 * @author Pablo-VE
 */

@Service
public class EmpleadoServiceImplementation implements IEmpleadoService{
    
    @Autowired
    private IEmpleadoRepository empleadoRepositoy;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleado>> findAll() {
        return Optional.ofNullable(empleadoRepositoy.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Empleado> findById(Long id) {
        return empleadoRepositoy.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Empleado> findByCedula(String cedula) {
        return Optional.ofNullable(empleadoRepositoy.findByCedula(cedula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleado>> findByCedulaContaining(String cedula) {
        return Optional.ofNullable(empleadoRepositoy.findByCedulaContaining(cedula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleado>> findByNombreContainingIgnoreCase(String nombre) {
        return Optional.ofNullable(empleadoRepositoy.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleado>> findByEstado(boolean estado) {
        return Optional.ofNullable(empleadoRepositoy.findByEstado(estado));
    }

    @Override
    @Transactional
    public Empleado create(Empleado empleado) {
        return empleadoRepositoy.save(empleado);
    }

    @Override
    @Transactional
    public Optional<Empleado> update(Empleado empleado, Long id) {
        if (empleadoRepositoy.findById(id).isPresent()) {
            
            return Optional.ofNullable(empleadoRepositoy.save(empleado));
        } else {
            return null;
        }
    }
    
}
