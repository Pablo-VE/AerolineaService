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
import org.una.aerolinea.entities.Avion;

/**
 *
 * @author Pablo-VE
 */
public interface IAvionRepository extends JpaRepository<Avion, Long>{
    public List<Avion> findByMatricula(String matricula);
    public List<Avion> findByMatriculaContainingIgnoreCase(String matricula);
    public List<Avion> findByEstado(boolean estado);
    
    @Query("SELECT a FROM Avion a LEFT JOIN a.aerolinea e WHERE e.id = :aerolineaID")
    public List<Avion> findByAerolinea(@Param("aerolineaID")Long aerolinea);
    
    @Query("SELECT a FROM Avion a LEFT JOIN a.tipoAvion t WHERE t.id = :tipoAvionID")
    public List<Avion> findByTipoAvion(@Param("tipoAvionID") Long tipoAvion);
    
}
