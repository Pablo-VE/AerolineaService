/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.json.bind.annotation.JsonbDateFormat;
import org.una.aerolinea.dto.VueloDTO;

/**
 *
 * @author Pablo-VE
 */
public interface IVueloService {
    public Optional<List<VueloDTO>> findAll();

    public Optional<VueloDTO> findById(Long id);
    
    public Optional<List<VueloDTO>>findByFecha(@JsonbDateFormat(value = "yyyy-MM-dd")Date fecha);
    
    public Optional<List<VueloDTO>> findByEstado(boolean estado);
    
    public Optional<List<VueloDTO>> findByAvion(Long avion);
    
    public Optional<List<VueloDTO>> findByRuta(Long ruta);

    public VueloDTO create(VueloDTO vuelo);

    public Optional<VueloDTO> update(VueloDTO vuelo, Long id);
    
}
