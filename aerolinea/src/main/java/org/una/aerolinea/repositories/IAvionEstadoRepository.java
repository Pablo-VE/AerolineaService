/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.AvionEstado;
/**
 *
 * @author Luis
 */
public interface IAvionEstadoRepository extends JpaRepository<AvionEstado, Long> {
    public List<AvionEstado> findByCombustible(int combustible);
    public List<AvionEstado> findByDistanciaRecorrida(int distanciaRecorrida);   
    public List<AvionEstado> findByEstado(boolean estado);   
    public List<AvionEstado> findByTiempoTierra(int tiempoTierra); 
    public List<AvionEstado> findByUbicacionContainingIgnoreCase(String ubicacion); 
}
