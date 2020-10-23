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
    
    @Query("SELECT u FROM Usuario u LEFT JOIN u.empleado e WHERE e.cedula = :cedula AND u.passwordEncriptado = :password")
    public Usuario findByCedulaAndPasswordEncriptado(@Param("cedula")String cedula, @Param("password")String passwordEncriptado);
    
    @Query("SELECT u FROM Usuario u LEFT JOIN u.empleado e WHERE e.cedula = :cedula")
    public Optional<Usuario> findByCedula(@Param("cedula")String cedula);
    
    public List<Usuario> findByEstado(boolean estado);
    
    
    @Query("SELECT u FROM Usuario u LEFT JOIN u.rol r WHERE r.id = :rolID")
    public List<Usuario> findByRol(@Param("rolID")Long rol);
    
    @Query("SELECT u FROM Usuario u WHERE UPPER(u.empleado.cedula) like CONCAT('%', UPPER(:cedula), '%')")
    public List<Usuario> findByCedulaEmpleado(@Param("cedula")String cedula);
    
    @Query("SELECT u FROM Usuario u WHERE UPPER(u.empleado.nombre) like CONCAT('%', UPPER(:nombre), '%')")
    public List<Usuario> findByNombreEmpleado(@Param("nombre")String nombre);
    
}
