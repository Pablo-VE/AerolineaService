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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aerolinea.entities.Vuelo;
import org.una.aerolinea.repositories.IVueloRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class VueloServiceImplementation implements IVueloService{
    @Autowired
    private IVueloRepository vueloRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findAll() {
        return Optional.ofNullable(vueloRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vuelo> findById(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findByFecha(@JsonbDateFormat(value = "yyyy-MM-dd")Date fecha) {
        return Optional.ofNullable(vueloRepository.findByFecha(fecha));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findByEstado(boolean estado) {
        return Optional.ofNullable(vueloRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public Vuelo create(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    @Transactional
    public Optional<Vuelo> update(Vuelo vuelo, Long id) {
        if(vueloRepository.findById(id).isPresent()){
            return Optional.ofNullable(vueloRepository.save(vuelo));
        }else{
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findByAvion(Long avion) {
        return Optional.ofNullable(vueloRepository.findByAvion(avion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findByRuta(Long ruta) {
        return Optional.ofNullable(vueloRepository.findByRuta(ruta));
    }
}
