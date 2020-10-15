/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.TipoServicioAeropuerto;

/**
 *
 * @author Luis
 */
public interface ITipoServicioAeropuertoRepository extends JpaRepository<TipoServicioAeropuerto, Long> {
    public List<TipoServicioAeropuerto> findByNombreContainingIgnoreCase(String nombre);
    public List<TipoServicioAeropuerto> findByDescripcion(String descripcion);   
    public List<TipoServicioAeropuerto> findByEstado(boolean estado);   
}
