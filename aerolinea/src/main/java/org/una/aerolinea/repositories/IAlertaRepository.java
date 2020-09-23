/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.Alerta;

/**
 *
 * @author Pablo-VE
 */
public interface IAlertaRepository extends JpaRepository<Alerta, Long>{
    public List<Alerta> findByEstado(boolean estado);
    public Alerta findByVuelo(Long vuelo);
    public List<Alerta> findByDescripcionContainingIgnoreCase(String descripcion);
    
}
