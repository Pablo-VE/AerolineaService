/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.BitacoraAvion;
/**
 *
 * @author Luis
 */
public interface IBitacoraAvionRepository extends JpaRepository<BitacoraAvion, Long> {
    public List<BitacoraAvion> findByCombustible(int combustible);
    public List<BitacoraAvion> findByDistanciaRecorrida(int distanciaRecorrida);   
    public List<BitacoraAvion> findByEstado(boolean estado);   
    public List<BitacoraAvion> findByTiempoTierra(int tiempoTierra); 
    public List<BitacoraAvion> findByUbicacionContainingIgnoreCase(String ubicacion); 
}
