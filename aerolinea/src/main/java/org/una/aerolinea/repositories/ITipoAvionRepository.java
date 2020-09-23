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
import org.una.aerolinea.entities.TipoAvion;

/**
 *
 * @author Pablo-VE
 */
public interface ITipoAvionRepository extends JpaRepository<TipoAvion, Long>{
    public List<TipoAvion> findByNombreContainingIgnoreCase(String nombre);
    public List<TipoAvion> findByEstado(boolean estado);
    @Query("SELECT t FROM TipoAvion t WHERE t.distanciaRecomendada <= :distanciaMaxima AND t.distanciaRecomendada >= :distanciaMinima")
    public List<TipoAvion> findByDistanciaRango(@Param("distanciaMaxima")float distanciaMaxima, @Param("distanciaMinima")float distanciaMinima);
    
    
    
}
