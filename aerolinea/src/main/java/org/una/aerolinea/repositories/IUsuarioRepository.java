/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aerolinea.entities.Usuario;

/**
 *
 * @author Pablo-VE
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT u FROM Usuario u LEFT JOIN u.empleado e WHERE u.passwordEncriptado = :password AND e.cedula = :cedula")
    public Usuario findByCedulaAndPassword(@Param("cedula")String cedula, @Param("password")String password);
    public List<Usuario> findByEstado(boolean estado);
    public List<Usuario> findByRol(Long rol);
    
    
}
