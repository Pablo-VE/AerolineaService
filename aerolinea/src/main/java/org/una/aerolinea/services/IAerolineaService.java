/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.AerolineaDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IAerolineaService {
    public Optional<List<AerolineaDTO>> findAll();

    public Optional<AerolineaDTO> findById(Long id);

    public Optional<List<AerolineaDTO>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<AerolineaDTO>> findByResponsableContainingIgnoreCase(String responsable);
    
    public Optional<List<AerolineaDTO>> findByEstado(boolean estado);

    public AerolineaDTO create(AerolineaDTO aerolinea);

    public Optional<AerolineaDTO> update(AerolineaDTO aerolinea, Long id);
    
}
