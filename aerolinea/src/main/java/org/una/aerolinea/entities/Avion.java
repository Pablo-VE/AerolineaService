/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "aviones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Avion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 20, unique = true)
    private String matricula;

    @Column(length = 50)
    private String ubicacion;
       
    @ManyToOne 
    @JoinColumn(name="servicios_aeropuerto_id")
    private ServicioBrindadoAeropuerto servicioAeropuerto;
    
    @ManyToOne 
    @JoinColumn(name="aerolineas_id")
    private Aerolinea aerolinea;
    
    @ManyToOne 
    @JoinColumn(name="tipos_aviones_id")
    private TipoAvion tipoAvion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avion") 
    private List<Vuelo> vuelos= new ArrayList<>();
    
    @Column
    private boolean estado;
}
