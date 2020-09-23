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
import org.una.aerolinea.entities.Vuelo;

/**
 *
 * @author Pablo-VE
 */
public interface IVueloService {
    public Optional<List<Vuelo>> findAll();

    public Optional<Vuelo> findById(Long id);
    
    public Optional<List<Vuelo>>findByFecha(@JsonbDateFormat(value = "yyyy-MM-dd")Date fecha);
    
    public Optional<List<Vuelo>> findByEstado(boolean estado);
    
    public Optional<List<Vuelo>> findByAvion(Long avion);
    
    public Optional<List<Vuelo>> findByRuta(Long ruta);

    public Vuelo create(Vuelo vuelo);

    public Optional<Vuelo> update(Vuelo vuelo, Long id);
    
}
