/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Pablo-VE
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class AvionDTO {
    private Long id;
    private String matricula;
    private int combustible;
    private int tiempoTierra;
    private float distanciaRecorrida;
    private String ubicacion;
    private AerolineaDTO aerolinea;
    private TipoAvionDTO tipoAvion;
    private List<VueloDTO> vuelos;
    private boolean estado;
    
}
