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
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100, name = "password_encriptado")
    private String passwordEncriptado;
    
    @Column(length = 25, unique = true, nullable = false)
    private String cedula;
        
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleados_id", referencedColumnName = "id")
    private Empleado empleado;
    
 
    @ManyToOne 
    @JoinColumn(name="roles_id")
    private Rol rol;
    
    @Column
    private boolean estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario") 
    private List<Transaccion> transacciones = new ArrayList<>();

    
    
}