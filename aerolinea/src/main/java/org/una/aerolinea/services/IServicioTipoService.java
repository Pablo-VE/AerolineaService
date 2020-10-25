/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.ServicioTipoDTO;
/**
 *
 * @author Luis
 */
public interface IServicioTipoService {
    
    public Optional<List<ServicioTipoDTO>> findAll();

    public Optional<ServicioTipoDTO> findById(Long id);

    public Optional<List<ServicioTipoDTO>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<ServicioTipoDTO>> findByEstado(boolean estado);
    
    public Optional<List<ServicioTipoDTO>> findByDescripcionContainingIgnoreCase(String descripcion);

    public ServicioTipoDTO create(ServicioTipoDTO tipoServicioAeropuerto);

    public Optional<ServicioTipoDTO> update(ServicioTipoDTO tipoServicioAeropuerto, Long id);
   
    public Optional<List<ServicioTipoDTO>> findByAreaTrabajo(Long areaTrabajo);
}
