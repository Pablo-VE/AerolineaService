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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 25, unique = true, nullable = false)
    private String cedula;
    
    @Column(length = 25)
    private String telefono;
    
    @Column(length = 250)
    private String direccion;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado") 
    private List<Horario> horarios= new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado") 
    private List<HoraMarcaje> horasMarcajes= new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable") 
    private List<ServicioBrindadoAeropuerto> serviciosAeropuerto= new ArrayList<>();
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jefe") 
    private List<Empleado> subempleados= new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado") 
    private List<TrabajoEmpleado> trabajosEmpleado= new ArrayList<>();
    
    @ManyToOne 
    @JoinColumn(name="empleados_id")
    private Empleado jefe;

       
//    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Usuario usuario;
    
    @Column
    private boolean estado;


    private static final long serialVersionUID = 1L;
    
    
    
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