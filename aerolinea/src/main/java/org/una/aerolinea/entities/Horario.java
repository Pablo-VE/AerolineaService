/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Pablo-VE
 */
@Entity
@Table(name = "horarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Horario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_inicio", nullable = false)
    private int diaInicio;
    
    
    @Column(name = "hora_inicio", nullable = false)
    @Temporal(TemporalType.TIME)
    @Setter(AccessLevel.NONE)
    private Date horaInicio;
    
    @Column(name = "dia_final", nullable = false)
    private int diaFinal;
    
  
    
    
    @Column(name = "hora_final", nullable = false)
    @Temporal(TemporalType.TIME)
    @Setter(AccessLevel.NONE)
    private Date horaFinal;
    
    @ManyToOne 
    @JoinColumn(name="empleados_id")
    private Empleado empleado;
    
    @Column
    private boolean estado;
    
    
}
