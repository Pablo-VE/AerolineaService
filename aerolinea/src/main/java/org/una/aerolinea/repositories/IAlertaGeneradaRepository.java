/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.AlertaGenerada;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Pablo-VE
 */
public interface IAlertaGeneradaRepository extends JpaRepository<AlertaGenerada, Long> {
    public List<AlertaGenerada> findByEstado(boolean estado);
    
    @Query("SELECT ag FROM AlertaGenerada ag LEFT JOIN ag.tipoAlerta ta WHERE ta.id = :tipoAlertaID")
    public List<AlertaGenerada> findByTipoAlertaSQL(@Param("tipoAlertaID") Long tipoAlerta);
    
    @Query("SELECT ag FROM AlertaGenerada ag LEFT JOIN ag.vuelo v WHERE v.id = :vueloID")
    public List<AlertaGenerada> findByVueloSQL(@Param("vueloID") Long vuelo);
}
