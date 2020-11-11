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
import org.una.aerolinea.entities.ParametroAplicacion;

/**
 *
 * @author Pablo-VE
 */
public interface IParametroAplicacionRepository extends JpaRepository<ParametroAplicacion, Long> {
    public List<ParametroAplicacion> findByNombreContainingIgnoreCase(String nombre);
    public List<ParametroAplicacion> findByDescripcionContainingIgnoreCase(String nombre);
    public List<ParametroAplicacion> findByEstado(boolean estado);
    
    @Query("SELECT u FROM ParametroAplicacion u WHERE u.nombre = :nombre AND u.valor = :valor")
    public Optional<ParametroAplicacion> findByNombreAndValor(@Param("nombre")String nombre, @Param("valor")String valor);
    
}
