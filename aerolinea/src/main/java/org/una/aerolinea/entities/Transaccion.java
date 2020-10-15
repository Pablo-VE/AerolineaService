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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "transacciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaccion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 200)
    private String descripcion;
    
    @Column(length = 100)
    private String lugar;
    
    @Column(length = 10)
    private String rol;
    
    @ManyToOne 
    @JoinColumn(name="usuarios_id")
    private Usuario usuario;
    
    @Column(name = "fecha_registro", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @Column
    private boolean estado;

    
    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
    }
}
