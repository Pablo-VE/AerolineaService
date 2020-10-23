/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.TransaccionDTO;

/**
 *
 * @author Pablo-VE
 */
public interface ITransaccionService {
    public Optional<List<TransaccionDTO>> findAll();

    public Optional<TransaccionDTO> findById(Long id);

    public Optional<List<TransaccionDTO>> findByLugarContainingIgnoreCase(String lugar);
    
    public Optional<List<TransaccionDTO>> findByDescripcionContainingIgnoreCase(String descripcion);
    
    public Optional<List<TransaccionDTO>> findByEstado(boolean estado);
    
    public Optional<List<TransaccionDTO>> findByRol(String rol);

    public TransaccionDTO create(TransaccionDTO transaccion);

    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id);
    
}
