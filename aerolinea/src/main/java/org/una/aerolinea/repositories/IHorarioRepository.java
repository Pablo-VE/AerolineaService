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
import org.una.aerolinea.entities.Horario;

/**
 *
 * @author Pablo-VE
 */
public interface IHorarioRepository extends JpaRepository<Horario, Long>{
    
    @Query("SELECT u FROM Horario u LEFT JOIN u.empleado r WHERE r.id = :empleadoID")
     public List<Horario> findByEmpleado(@Param("empleadoID")Long empleado);
     
     public List<Horario> findByDiaInicio(String diaInicio);
     public List<Horario> findByEstado(boolean estado);
     
     @Query("SELECT u FROM Horario u LEFT JOIN u.empleado r WHERE r.id = :empleadoID AND u.estado = :estado")
     public List<Horario> findByEmpleadoAndEstado(@Param("empleadoID")Long empleado, @Param("estado") boolean estado);
    
}
