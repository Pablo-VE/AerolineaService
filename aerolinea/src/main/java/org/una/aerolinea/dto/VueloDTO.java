/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.dto;

import java.util.Date;
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
public class VueloDTO {
    private Long id;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date fecha;
    private AvionDTO avion;
    private boolean estado;
    private RutaDTO ruta;
    private AlertaDTO alerta;
    
}
