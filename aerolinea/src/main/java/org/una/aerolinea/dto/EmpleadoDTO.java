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
public class EmpleadoDTO {
    private Long id; 
    private String nombre;   
    private String cedula; 
    private String telefono; 
    private String direccion; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion; 
    private List<EmpleadoDTO> subempleados;
    private EmpleadoDTO jefe;
   // private UsuarioDTO usuario;
    private boolean estado;
    private List<TrabajoEmpleadoDTO> trabajosEmpleado;
    private List<HorarioDTO> horarios;
    private List<HoraMarcajeDTO> horasMarcajes;
    private List<ServicioAeropuertoDTO> serviciosAeropuerto;
    
}
