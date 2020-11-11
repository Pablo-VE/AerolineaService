/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.ParametroAplicacionDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IParametroAplicacionService {
    public Optional<List<ParametroAplicacionDTO>> findAll();
    
    public Optional<ParametroAplicacionDTO> findByNombreAndValor(String nombre, String valor);

    public Optional<ParametroAplicacionDTO> findById(Long id);

    public Optional<List<ParametroAplicacionDTO>> findByNombreContainingIgnoreCase(String nombre);
    
    public Optional<List<ParametroAplicacionDTO>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<ParametroAplicacionDTO>> findByEstado(boolean estado);

    public ParametroAplicacionDTO create(ParametroAplicacionDTO parametroAplicacion);
    
    public ParametroAplicacionDTO createPasswordAutorizacion(ParametroAplicacionDTO parametroAplicacion);
    
    public Optional<ParametroAplicacionDTO> updatePasswordAutorizacion(ParametroAplicacionDTO parametroAplicacion, Long id);

    public Optional<ParametroAplicacionDTO> update(ParametroAplicacionDTO parametroAplicacion, Long id);
    
}
