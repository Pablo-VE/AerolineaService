/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.ServicioTipo;

/**
 *
 * @author Luis
 */
public interface IServicioTipoRepository extends JpaRepository<ServicioTipo, Long> {
    public List<ServicioTipo> findByNombreContainingIgnoreCase(String nombre);
    public List<ServicioTipo> findByDescripcion(String descripcion);   
    public List<ServicioTipo> findByEstado(boolean estado);   
}
