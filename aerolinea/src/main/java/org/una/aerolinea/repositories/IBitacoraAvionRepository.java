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
    
    @Query("SELECT b FROM BitacoraAvion b LEFT JOIN b.avion a WHERE a.id = :avionId")
    public List<BitacoraAvion> findByAvion(@Param("avionId")String tipo);
    
    @Query("SELECT b FROM BitacoraAvion b WHERE b.distanciaRecorrida <= :distanciaMaxima AND b.distanciaRecorrida >= :distanciaMinima")
    public List<BitacoraAvion> findByDistanciaRecorridaRango(@Param("distanciaMaxima")int distanciaMaxima, @Param("distanciaMinima")int distanciaMinima);
    
    
    @Query("SELECT b FROM BitacoraAvion b WHERE b.combustible <= :conbustibleMaximo AND b.combustible >= :conbustibleMinimo")
    public List<BitacoraAvion> findByCombustibleRango(@Param("conbustibleMaximo")int conbustibleMaximo, @Param("conbustibleMinimo")int conbustibleMinimo);

    @Query("SELECT b FROM BitacoraAvion b WHERE b.tiempoTierra <= :tiempoMaximo AND b.tiempoTierra >= :tiempoMinimo")
    public List<BitacoraAvion> findByTiempoTierraRango(@Param("tiempoMaximo")int tiempoMaximo, @Param("tiempoMinimo")int tiempoMinimo);
    
}
