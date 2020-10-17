/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.dto;

import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
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
public class ServicioRegistradoDTO {
    private Long id;
    private ServicioTipoDTO servicioTipo;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private java.util.Date fechaRegistro;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private java.util.Date fechaModificacion;
    private float cobro;
    private float estadoCobro;
    private float duracion;
    private String observaciones;
    private boolean estado;
    private EmpleadoDTO responsable;
    private AvionDTO avion;
    
}
