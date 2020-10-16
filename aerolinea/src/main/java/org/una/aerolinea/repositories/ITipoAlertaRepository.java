/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.TipoAlerta;

/**
 *
 * @author Pablo-VE
 */
public interface ITipoAlertaRepository extends JpaRepository<TipoAlerta, Long>{
    public List<TipoAlerta> findByEstado(boolean estado);
    public TipoAlerta findByVuelo(Long vuelo);
    public List<TipoAlerta> findByDescripcionContainingIgnoreCase(String descripcion);
    
}
