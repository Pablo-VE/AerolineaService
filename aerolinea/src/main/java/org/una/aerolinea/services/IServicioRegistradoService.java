/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.ServicioRegistrado;

/**
 *
 * @author Pablo-VE
 */
public interface IServicioRegistradoService {
    public Optional<List<ServicioRegistrado>> findAll();

    public Optional<ServicioRegistrado> findById(Long id);
    
    public Optional<List<ServicioRegistrado>> findByCobroRango(float cobroMas, float cobroMenos);

    public Optional<List<ServicioRegistrado>> findByTipoContainingIgnoreCase(String tipo);
    
    public Optional<List<ServicioRegistrado>> findByEstadoCobro(boolean estadoCobro);
    
    public Optional<List<ServicioRegistrado>> findByEstado(boolean estado);
    
    public ServicioRegistrado create(ServicioRegistrado servicioAeropuerto);
    
    public Optional<ServicioRegistrado> update(ServicioRegistrado servicioAeropuerto, Long id);
    
}
