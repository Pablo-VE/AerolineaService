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
import org.una.aerolinea.entities.ServicioTipo;

/**
 *
 * @author Luis
 */
public interface IServicioTipoRepository extends JpaRepository<ServicioTipo, Long> {
    public List<ServicioTipo> findByNombreContainingIgnoreCase(String nombre);
    public List<ServicioTipo> findByDescripcionContainingIgnoreCase(String descripcion);   
    public List<ServicioTipo> findByEstado(boolean estado);  
   
    @Query("SELECT u FROM ServicioTipo u LEFT JOIN u.areaTrabajo r WHERE r.id = :areaTrabajoID")
    public List<ServicioTipo> findByAreaTrabajo(@Param("areaTrabajoID")Long areaTrabajo);
}
