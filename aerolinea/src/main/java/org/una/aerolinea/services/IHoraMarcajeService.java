/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.HoraMarcaje;

/**
 *
 * @author Pablo-VE
 */
public interface IHoraMarcajeService {
    public Optional<List<HoraMarcaje>> findAll();

    public Optional<HoraMarcaje> findById(Long id);
    
    public Optional<List<HoraMarcaje>> findByEmpleado(Long empleado);

    public Optional<List<HoraMarcaje>> findByTipo(int tipo);
    
    public Optional<List<HoraMarcaje>> findByEstado(boolean estado);
    
    public HoraMarcaje create(HoraMarcaje horaMarcaje);

    public Optional<HoraMarcaje> update(HoraMarcaje horaMarcaje, Long id);
    
}
