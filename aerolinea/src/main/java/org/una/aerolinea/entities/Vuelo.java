/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "vuelos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vuelo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fecha;
    
    @ManyToOne 
    @JoinColumn(name="aviones_id", nullable = false)
    private Avion avion;
    
    @ManyToOne 
    @JoinColumn(name="rutas_id", nullable = false)
    private Ruta ruta;
    
    @Column
    private boolean estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipos_alertas_id", referencedColumnName = "id")
    private TipoAlerta tipoAlerta;
    
    @PrePersist
    public void prePersist() {
        fecha= new Date();
    }
    
    @PreUpdate
    public void preUpdate() {
        fecha = new Date();   
    }  
    
}
