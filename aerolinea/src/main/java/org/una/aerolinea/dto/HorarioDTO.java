/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.dto;

import java.util.Date;
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
public class HorarioDTO {
    private Long id;
    private int diaInicio;
    private Date horaInicio;
    private int diaFinal;
    private Date horaFinal;
    private EmpleadoDTO empleado;
    private boolean estado;
    
}
