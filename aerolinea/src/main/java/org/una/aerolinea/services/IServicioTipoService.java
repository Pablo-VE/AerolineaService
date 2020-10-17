/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.entities.ServicioTipo;
/**
 *
 * @author Luis
 */
public interface IServicioTipoService {
    
    public Optional<List<ServicioTipo>> findAll();

    public Optional<ServicioTipo> findById(Long id);

    public Optional<List<ServicioTipo>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<ServicioTipo>> findByEstado(boolean estado);
    
    public Optional<List<ServicioTipo>> findByDescripcion(String descripcion);

    public ServicioTipo create(ServicioTipo tipoServicioAeropuerto);

    public Optional<ServicioTipo> update(ServicioTipo tipoServicioAeropuerto, Long id);
   
}
