/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aerolinea.entities.TrabajoEmpleado;

/**
 *
 * @author Pablo-VE
 */
public interface ITrabajoEmpleadoRepository extends JpaRepository<TrabajoEmpleado, Long>{
    public List<TrabajoEmpleado> findByEstado(boolean estado);
    
    @Query("SELECT u FROM TrabajoEmpleado u LEFT JOIN u.empleado r WHERE r.id = :empleadoID")
    public List<TrabajoEmpleado> findByEmpleado(@Param("empleadoID")Long empleado);
    
    @Query("SELECT u FROM TrabajoEmpleado u LEFT JOIN u.areaTrabajo r WHERE r.id = :areaTrabajoID")
    public List<TrabajoEmpleado> findByAreaTrabajo(@Param("areaTrabajoID")Long areaTrabajo);
    

}
