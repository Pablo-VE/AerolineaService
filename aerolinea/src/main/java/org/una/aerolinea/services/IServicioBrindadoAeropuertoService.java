/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.ServicioBrindadoAeropuerto;

/**
 *
 * @author Pablo-VE
 */
public interface IServicioBrindadoAeropuertoService {
    public Optional<List<ServicioBrindadoAeropuerto>> findAll();

    public Optional<ServicioBrindadoAeropuerto> findById(Long id);
    
    public Optional<List<ServicioBrindadoAeropuerto>> findByCobroRango(float cobroMas, float cobroMenos);

    public Optional<List<ServicioBrindadoAeropuerto>> findByTipoContainingIgnoreCase(String tipo);
    
    public Optional<List<ServicioBrindadoAeropuerto>> findByEstadoCobro(boolean estadoCobro);
    
    public Optional<List<ServicioBrindadoAeropuerto>> findByEstado(boolean estado);
    
    public ServicioBrindadoAeropuerto create(ServicioBrindadoAeropuerto servicioAeropuerto);
    
    public Optional<ServicioBrindadoAeropuerto> update(ServicioBrindadoAeropuerto servicioAeropuerto, Long id);
    
}
