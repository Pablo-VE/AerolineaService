/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.AvionDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IAvionService {
    public Optional<List<AvionDTO>> findAll();

    public Optional<AvionDTO> findById(Long id);
    
    public Optional<List<AvionDTO>> findByMatricula(String matricula);

    public Optional<List<AvionDTO>> findByMatriculaContainingIgnoreCase(String matricula);
    
    public Optional<List<AvionDTO>> findByEstado(boolean estado);
    
    public Optional<List<AvionDTO>> findByAerolinea(Long aerolinea);
    
    public Optional<List<AvionDTO>> findByTipoAvion(Long tipoAvion);

    public AvionDTO create(AvionDTO avion);

    public Optional<AvionDTO> update(AvionDTO avion, Long id);
    
}
