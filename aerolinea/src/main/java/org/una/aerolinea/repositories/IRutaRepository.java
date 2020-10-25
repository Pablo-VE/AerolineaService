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
import org.una.aerolinea.entities.Ruta;

/**
 *
 * @author Pablo-VE
 */
public interface IRutaRepository extends JpaRepository<Ruta, Long>{
    public List<Ruta> findByOrigenContainingIgnoreCase(String origen);
    public List<Ruta> findByDestinoContainingIgnoreCase(String destino);
    public List<Ruta> findByEstado(boolean estado);
    
    
    @Query("SELECT t FROM Ruta t WHERE t.distancia <= :distanciaMaxima AND t.distancia >= :distanciaMinima")
    public List<Ruta> findByDistanciaRango(@Param("distanciaMaxima")float distanciaMaxima, @Param("distanciaMinima")float distanciaMinima);
    
    
}
