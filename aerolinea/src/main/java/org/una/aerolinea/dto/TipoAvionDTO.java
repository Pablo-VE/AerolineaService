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
public class TipoAvionDTO {
    private Long id;
    private String nombre;
    private float distanciaRecomendada;
    private float distanciaMaxima;
    private boolean estado;
    
}
