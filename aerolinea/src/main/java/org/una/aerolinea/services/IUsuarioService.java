/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.una.aerolinea.dto.UsuarioDTO;
/**
 *
 * @author Pablo-VE
 */
public interface IUsuarioService {
    public Optional<List<UsuarioDTO>> findAll();
    
    public Optional<List<UsuarioDTO>> findByEstado(boolean estado);
    
    public Optional<UsuarioDTO> findByCedula(String cedula);
    
    public Optional<List<UsuarioDTO>> findByRol(Long rol);

    public Optional<UsuarioDTO> findById(Long id);

    public UsuarioDTO create(UsuarioDTO usuario);

    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id);
    
    
}
