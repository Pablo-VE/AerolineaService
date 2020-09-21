/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.Rol;

/**
 *
 * @author Pablo-VE
 */
public interface IRolRepository extends JpaRepository<Rol, Long>{
    public List<Rol> findByNombreContainingIgnoreCase(String nombre);
    public List<Rol> findByDescripcionContainingIgnoreCase(String nombre);
    public List<Rol> findByEstado(boolean estado);
    
}
