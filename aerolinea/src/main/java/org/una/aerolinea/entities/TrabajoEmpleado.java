/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Pablo-VE
 */
@Entity
@Table(name = "trabajos_empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrabajoEmpleado implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="empleados_id")
    private Empleado empleado;
    
    @ManyToOne 
    @JoinColumn(name="areas_trabajos_id")
    private AreaTrabajo areaTrabajo;
    
    @Column
    private boolean estado;
}
