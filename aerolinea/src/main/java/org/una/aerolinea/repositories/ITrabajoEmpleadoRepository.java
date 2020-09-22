/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.TrabajoEmpleado;

/**
 *
 * @author Pablo-VE
 */
public interface ITrabajoEmpleadoRepository extends JpaRepository<TrabajoEmpleado, Long>{
    public List<TrabajoEmpleado> findByEstado(boolean estado);
    public List<TrabajoEmpleado> findByEmpleado(Long empleado);
    public List<TrabajoEmpleado> findByAreaTrabajo(Long areaTrabajo);
}
