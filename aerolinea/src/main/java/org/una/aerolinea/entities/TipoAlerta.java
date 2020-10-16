/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
@Table(name = "tipos_alertas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoAlerta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private boolean estado;
    
    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fecha;
    
    @Column(length = 200)
    private String descripcion;
    
    
    
    @PrePersist
    public void prePersist() {
        fecha = new Date();
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAlerta") 
    private List<AlertaGenerada> alertasGeneradas = new ArrayList<>();
    
}
