/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.Aerolinea;

/**
 *
 * @author Pablo-VE
 */
public interface IAerolineaRepository extends JpaRepository<Aerolinea, Long> {
    public List<Aerolinea> findByNombreContainingIgnoreCase(String nombre);
    public List<Aerolinea> findByResponsableContainingIgnoreCase(String responsable);
    public List<Aerolinea> findByEstado(boolean estado);
    
}
