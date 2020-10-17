/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.dto;

import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Luis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class BitacoraAvionDTO {
    private Long id;
    private float distanciaRecorrida;
    private String ubicacion;
    private int tiempoTierra;
    private int combustible;
    private boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private java.util.Date fechaRegistro;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private java.util.Date fechaModificacion;
    private AvionDTO avion;

    
}
