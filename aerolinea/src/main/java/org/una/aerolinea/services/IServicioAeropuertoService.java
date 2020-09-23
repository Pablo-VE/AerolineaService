/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.ServicioAeropuerto;

/**
 *
 * @author Pablo-VE
 */
public interface IServicioAeropuertoService {
    public Optional<List<ServicioAeropuerto>> findAll();

    public Optional<ServicioAeropuerto> findById(Long id);
    
    public Optional<List<ServicioAeropuerto>> findByCobroRango(float cobroMas, float cobroMenos);

    public Optional<List<ServicioAeropuerto>> findByTipoContainingIgnoreCase(String tipo);
    
    public Optional<List<ServicioAeropuerto>> findByEstadoCobro(boolean estadoCobro);
    
    public Optional<List<ServicioAeropuerto>> findByEstado(boolean estado);
    
    public ServicioAeropuerto create(ServicioAeropuerto servicioAeropuerto);
    
    public Optional<ServicioAeropuerto> update(ServicioAeropuerto servicioAeropuerto, Long id);
    
}
