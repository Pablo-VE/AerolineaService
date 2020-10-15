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
import org.una.aerolinea.entities.ServicioBrindadoAeropuerto;

/**
 *
 * @author Pablo-VE
 */
public interface IServicioBrindadoAeropuertoRepository extends JpaRepository<ServicioBrindadoAeropuerto, Long>{
    
    
    @Query("SELECT s FROM ServicioBrindadoAeropuerto s WHERE s.cobro <= :cobroMas AND s.cobro >= :cobroMenos")
    public List<ServicioBrindadoAeropuerto> findByCobroRango(@Param("cobroMas")float cobroMas, @Param("cobroMenos")float cobroMenos);
    public List<ServicioBrindadoAeropuerto> findByTipoContainingIgnoreCase(String nombreCompleto);
    public List<ServicioBrindadoAeropuerto> findByEstadoCobro(boolean estadoCobro);
    public List<ServicioBrindadoAeropuerto> findByEstado(boolean estado);
}
