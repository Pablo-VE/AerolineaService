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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "servicios_brindados_aeropuerto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServicioBrindadoAeropuerto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 20)
    private String tipo;
    
    @Column(name = "fecha_registro", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaModificacion;
    
    @Column
    private float cobro;
    
    @Column(name = "estado_cobro")
    private boolean estadoCobro;
    
    @Column
    private float duracion;
    
    @Column(length = 200)
    private String observaciones;
    
    @Column
    private boolean estado;
    
    @ManyToOne 
    @JoinColumn(name="responsable")
    private Empleado responsable;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioAeropuerto") 
    private List<Avion> aviones= new ArrayList<>();
    
    @ManyToOne 
    @JoinColumn(name="tipos_servicios_aeropuerto_id")
    private TipoServicioAeropuerto tipoServicioAeropuerto;
    
    
    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }
    
    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();   
    }
}
