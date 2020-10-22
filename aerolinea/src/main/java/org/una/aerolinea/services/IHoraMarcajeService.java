/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.HoraMarcajeDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IHoraMarcajeService {
    public Optional<List<HoraMarcajeDTO>> findAll();

    public Optional<HoraMarcajeDTO> findById(Long id);
    
    public Optional<List<HoraMarcajeDTO>> findByEmpleado(Long empleado);

    public Optional<List<HoraMarcajeDTO>> findByTipo(int tipo);
    
    public Optional<List<HoraMarcajeDTO>> findByEstado(boolean estado);
    
    public HoraMarcajeDTO create(HoraMarcajeDTO horaMarcaje);

    public Optional<HoraMarcajeDTO> update(HoraMarcajeDTO horaMarcaje, Long id);
    
}
