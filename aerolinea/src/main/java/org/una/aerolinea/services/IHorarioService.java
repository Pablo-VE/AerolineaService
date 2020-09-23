/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.Horario;

/**
 *
 * @author Pablo-VE
 */
public interface IHorarioService {
    public Optional<List<Horario>> findAll();
    
    public Optional<List<Horario>> findByEstado(boolean estado);
    
    public Optional<List<Horario>> findByEmpleado(Long empleado);
    
    public Optional<List<Horario>> findByEmpleadoAndEstado(Long empleado, boolean estado);

    public Optional<Horario> findById(Long id);

    public Horario create(Horario horario);

    public Optional<Horario> update(Horario horario, Long id);
    
}
