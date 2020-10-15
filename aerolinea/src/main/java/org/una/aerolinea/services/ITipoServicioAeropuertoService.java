/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.TipoServicioAeropuerto;
/**
 *
 * @author Luis
 */
public interface ITipoServicioAeropuertoService {
    
    public Optional<List<TipoServicioAeropuerto>> findAll();

    public Optional<TipoServicioAeropuerto> findById(Long id);

    public Optional<List<TipoServicioAeropuerto>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<TipoServicioAeropuerto>> findByEstado(boolean estado);
    
    public Optional<List<TipoServicioAeropuerto>> findByDescripcion(String descripcion);

    public TipoServicioAeropuerto create(TipoServicioAeropuerto tipoServicioAeropuerto);

    public Optional<TipoServicioAeropuerto> update(TipoServicioAeropuerto tipoServicioAeropuerto, Long id);
   
}
