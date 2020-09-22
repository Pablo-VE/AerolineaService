/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.AreaTrabajo;

/**
 *
 * @author Pablo-VE
 */
public interface IAreaTrabajoRepository extends JpaRepository<AreaTrabajo, Long>{
    public List<AreaTrabajo> findByNombreContainingIgnoreCase(String nombre);
    public List<AreaTrabajo> findByDescripcionContainingIgnoreCase(String nombre);
    public List<AreaTrabajo> findByEstado(boolean estado);
    
}
