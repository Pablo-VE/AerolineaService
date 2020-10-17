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
import org.una.aerolinea.entities.ServicioRegistrado;

/**
 *
 * @author Pablo-VE
 */
public interface IServicioRegistradoRepository extends JpaRepository<ServicioRegistrado, Long>{
    
    
    @Query("SELECT s FROM ServicioRegistrado s WHERE s.cobro <= :cobroMas AND s.cobro >= :cobroMenos")
    public List<ServicioRegistrado> findByCobroRango(@Param("cobroMas")float cobroMas, @Param("cobroMenos")float cobroMenos);
    public List<ServicioRegistrado> findByTipoContainingIgnoreCase(String nombreCompleto);
    public List<ServicioRegistrado> findByEstadoCobro(boolean estadoCobro);
    public List<ServicioRegistrado> findByEstado(boolean estado);
}
