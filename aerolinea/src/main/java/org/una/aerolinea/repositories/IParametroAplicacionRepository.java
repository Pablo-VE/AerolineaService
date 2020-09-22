/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.ParametroAplicacion;

/**
 *
 * @author Pablo-VE
 */
public interface IParametroAplicacionRepository extends JpaRepository<ParametroAplicacion, Long> {
    public List<ParametroAplicacion> findByNombreContainingIgnoreCase(String nombre);
    public List<ParametroAplicacion> findByDescripcionContainingIgnoreCase(String nombre);
    public List<ParametroAplicacion> findByEstado(boolean estado);
    
}
