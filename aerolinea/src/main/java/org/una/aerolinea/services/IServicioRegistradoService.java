/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.ServicioRegistradoDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IServicioRegistradoService {
    public Optional<List<ServicioRegistradoDTO>> findAll();

    public Optional<ServicioRegistradoDTO> findById(Long id);
    
    public Optional<List<ServicioRegistradoDTO>> findByCobroRango(float cobroMas, float cobroMenos);

    public Optional<List<ServicioRegistradoDTO>> findByTipoContainingIgnoreCase(String tipo);
    
    public Optional<List<ServicioRegistradoDTO>> findByEstadoCobro(boolean estadoCobro);
    
    public Optional<List<ServicioRegistradoDTO>> findByEstado(boolean estado);
    
    public ServicioRegistradoDTO create(ServicioRegistradoDTO servicioAeropuerto);
    
    public Optional<ServicioRegistradoDTO> update(ServicioRegistradoDTO servicioAeropuerto, Long id);
    
    public Optional<List<ServicioRegistradoDTO>> findByAvion(Long avion);
    
}
