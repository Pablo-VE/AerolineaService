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
import org.una.aerolinea.entities.HoraMarcaje;

/**
 *
 * @author Pablo-VE
 */
public interface IHoraMarcajeRepository extends JpaRepository<HoraMarcaje, Long>{
    public List<HoraMarcaje> findByEstado(boolean estado);
    
    @Query("SELECT u FROM HoraMarcaje u LEFT JOIN u.empleado r WHERE r.id = :empleadoID")
    public List<HoraMarcaje> findByEmpleado(@Param("empleadoID")Long empleado);
    public List<HoraMarcaje> findByTipo(int tipo);
    
}
