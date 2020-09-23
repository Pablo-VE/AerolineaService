/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Alerta;

/**
 *
 * @author Pablo-VE
 */
public interface IAlertaService {
    public Optional<List<Alerta>> findAll();

    public Optional<Alerta> findById(Long id);
    
    public Optional<List<Alerta>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<Alerta>> findByEstado(boolean estado);
    
    public Optional<Alerta> findByVuelo(Long vuelo);

    public Alerta create(Alerta alerta);

    public Optional<Alerta> update(Alerta alerta, Long id);
    
}
