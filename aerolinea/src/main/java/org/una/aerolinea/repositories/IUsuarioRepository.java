/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aerolinea.entities.Usuario;

/**
 *
 * @author Pablo-VE
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    
    
    public Usuario findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);
    public Optional<Usuario> findByCedula(String cedula);
    public List<Usuario> findByEstado(boolean estado);
    public List<Usuario> findByRol(Long rol);
    
    
}
