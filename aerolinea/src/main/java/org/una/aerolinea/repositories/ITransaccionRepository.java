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
import org.una.aerolinea.entities.Transaccion;

/**
 *
 * @author Pablo-VE
 */
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long>{
    public List<Transaccion> findByDescripcionContainingIgnoreCase(String descripcion);
    public List<Transaccion> findByLugarContainingIgnoreCase(String lugar);
    public List<Transaccion> findByEstado(boolean estado);
    
 
    public List<Transaccion> findByRolContainingIgnoreCase(String rol);
}
