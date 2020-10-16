/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.TipoAlerta;

/**
 *
 * @author Pablo-VE
 */
public interface ITipoAlertaService {
    public Optional<List<TipoAlerta>> findAll();

    public Optional<TipoAlerta> findById(Long id);
    
    public Optional<List<TipoAlerta>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<TipoAlerta>> findByEstado(boolean estado);
    
    public Optional<TipoAlerta> findByVuelo(Long vuelo);

    public TipoAlerta create(TipoAlerta alerta);

    public Optional<TipoAlerta> update(TipoAlerta alerta, Long id);
    
}
