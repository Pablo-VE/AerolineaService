/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aerolinea.entities.Horario;

/**
 *
 * @author Pablo-VE
 */
public interface IHorarioRepository extends JpaRepository<Horario, Long>{
     public List<Horario> findByEmpleado(Long empleado);
     public List<Horario> findByDiaInicio(String diaInicio);
     public List<Horario> findByEstado(boolean estado);
     public List<Horario> findByEmpleadoAndEstado(Long empleado, boolean estado);
    
}
