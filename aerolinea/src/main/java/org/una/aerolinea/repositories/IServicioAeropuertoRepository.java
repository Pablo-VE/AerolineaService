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
import org.una.aerolinea.entities.ServicioAeropuerto;

/**
 *
 * @author Pablo-VE
 */
public interface IServicioAeropuertoRepository extends JpaRepository<ServicioAeropuerto, Long>{
    
    
    @Query("SELECT s FROM ServicioAeropuerto s WHERE s.cobro <= :cobroMas AND s.cobro >= :cobroMenos")
    public List<ServicioAeropuerto> findByCobroRango(@Param("cobroMas")float cobroMas, @Param("cobroMenos")float cobroMenos);
    public List<ServicioAeropuerto> findByTipoContainingIgnoreCase(String nombreCompleto);
    public List<ServicioAeropuerto> findByEstadoCobro(boolean estadoCobro);
    public List<ServicioAeropuerto> findByEstado(boolean estado);
}
