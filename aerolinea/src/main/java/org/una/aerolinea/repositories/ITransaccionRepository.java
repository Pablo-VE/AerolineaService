/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.Transaccion;

/**
 *
 * @author Pablo-VE
 */
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long>{
    public List<Transaccion> findByDescripcionContainingIgnoreCase(String nombre);
    public List<Transaccion> findByLugarContainingIgnoreCase(String nombre);
    public List<Transaccion> findByEstado(boolean estado);
}
