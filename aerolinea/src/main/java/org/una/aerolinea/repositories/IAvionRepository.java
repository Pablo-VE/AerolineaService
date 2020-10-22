/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.Avion;

/**
 *
 * @author Pablo-VE
 */
public interface IAvionRepository extends JpaRepository<Avion, Long>{
    public List<Avion> findByMatricula(String matricula);
    public List<Avion> findByMatriculaContainingIgnoreCase(String matricula);
    public List<Avion> findByEstado(boolean estado);
    public List<Avion> findByAerolinea(Long aerolinea);
    public List<Avion> findByTipoAvion(Long tipoAvion);
    
}
