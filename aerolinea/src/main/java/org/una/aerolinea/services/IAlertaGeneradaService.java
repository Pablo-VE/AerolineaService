/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.AlertaGeneradaDTO;

/**
 *
 * @author Luis
 */
public interface IAlertaGeneradaService {
    
    public Optional<List<AlertaGeneradaDTO>> findAll();

    public Optional<AlertaGeneradaDTO> findById(Long id);

    public Optional<List<AlertaGeneradaDTO>> findByAutorizacion(String autorizacion);
    
    public Optional<List<AlertaGeneradaDTO>> findByEstado(boolean estado);
        
    public Optional<List<AlertaGeneradaDTO>> findByTipoAlerta(Long tipoAlerta);
    
    public Optional<List<AlertaGeneradaDTO>> findByVuelo(Long vuelo);

    public AlertaGeneradaDTO create(AlertaGeneradaDTO alertaGenerada);

    public Optional<AlertaGeneradaDTO> update(AlertaGeneradaDTO alertaGenerada, Long id);
}
