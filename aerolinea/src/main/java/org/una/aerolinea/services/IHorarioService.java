/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.HorarioDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IHorarioService {
    public Optional<List<HorarioDTO>> findAll();
    
    public Optional<List<HorarioDTO>> findByEstado(boolean estado);
    
    public Optional<List<HorarioDTO>> findByEmpleado(Long empleado);
    
    public Optional<List<HorarioDTO>> findByEmpleadoAndEstado(Long empleado, boolean estado);

    public Optional<HorarioDTO> findById(Long id);

    public HorarioDTO create(HorarioDTO horario);

    public Optional<HorarioDTO> update(HorarioDTO horario, Long id);
    
}
