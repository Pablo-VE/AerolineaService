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
import org.una.aerolinea.entities.Vuelo;

/**
 *
 * @author Pablo-VE
 */
public interface IVueloRepository extends JpaRepository<Vuelo, Long>{
    
    
    public List<Vuelo> findByEstado(boolean estado);
    public List<Vuelo> findByFecha(@JsonbDateFormat(value = "yyyy-MM-dd")Date fecha);
    public List<Vuelo> findByAvion(Long avion);
    public List<Vuelo> findByRuta(Long ruta);
    
}
