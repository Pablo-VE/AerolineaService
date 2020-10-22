/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.Empleado;
import org.una.aerolinea.dto.EmpleadoDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long>{
    
    public List<Empleado> findByCedulaContaining(String cedula);
    public List<Empleado> findByNombreContainingIgnoreCase(String nombreCompleto);
    public List<Empleado> findByEstado(boolean estado);
    public Optional<EmpleadoDTO> findByCedula(String cedula);
    
}
