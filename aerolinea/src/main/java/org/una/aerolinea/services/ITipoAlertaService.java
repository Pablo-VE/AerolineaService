/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.TipoAlertaDTO;

/**
 *
 * @author Pablo-VE
 */
public interface ITipoAlertaService {
    public Optional<List<TipoAlertaDTO>> findAll();

    public Optional<TipoAlertaDTO> findById(Long id);
    
    public Optional<List<TipoAlertaDTO>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<TipoAlertaDTO>> findByEstado(boolean estado);

    public TipoAlertaDTO create(TipoAlertaDTO alerta);

    public Optional<TipoAlertaDTO> update(TipoAlertaDTO alerta, Long id);
    
}
