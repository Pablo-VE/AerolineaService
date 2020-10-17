/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.AlertaGenerada;

/**
 *
 * @author Luis
 */
public interface IAlertaGeneradaService {
    
    public Optional<List<AlertaGenerada>> findAll();

    public Optional<AlertaGenerada> findById(Long id);

    public Optional<List<AlertaGenerada>> findByAutorizacion(String autorizacion);
    
    public Optional<List<AlertaGenerada>> findByEstado(boolean estado);
        
    public Optional<List<AlertaGenerada>> findByTipoAlerta(Long tipoAlerta);
    
    public Optional<List<AlertaGenerada>> findByVuelo(Long vuelo);

    public AlertaGenerada create(AlertaGenerada alertaGenerada);

    public Optional<AlertaGenerada> update(AlertaGenerada alertaGenerada, Long id);
}
