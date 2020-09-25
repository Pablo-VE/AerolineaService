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
@Table(name = "rutas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ruta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private float distancia;
    
    @Column(length = 25)
    private String origen;
    
    @Column(length = 25)
    private String destino;
    
    @Column
    private boolean estado;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruta") 
//    private List<Vuelo> vuelos= new ArrayList<>();
    
    
}
