/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aerolinea.entities.Vuelo;

/**
 *
 * @author Pablo-VE
 */
public interface IVueloRepository extends JpaRepository<Vuelo, Long>{
    
    
    public List<Vuelo> findByEstado(int estado);
    public List<Vuelo> findByFecha(@JsonbDateFormat(value = "yyyy-MM-dd")Date fecha);
    
    @Query("SELECT v FROM Vuelo v LEFT JOIN v.avion a WHERE a.id = :avionID")
    public List<Vuelo> findByAvion(@Param("avionID")Long avion);
    
    @Query("SELECT v FROM Vuelo v LEFT JOIN v.ruta r WHERE r.id = :rutaID")
    public List<Vuelo> findByRuta(@Param("rutaID")Long ruta);
    
}
