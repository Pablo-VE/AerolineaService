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
import org.una.aerolinea.dto.VueloDTO;
import org.una.aerolinea.repositories.IVueloRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

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
    public Optional<List<VueloDTO>> findAll() {
        return ServiceConvertionHelper.findList(vueloRepository.findAll(), VueloDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VueloDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(vueloRepository.findById(id), VueloDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByFecha(@JsonbDateFormat(value = "yyyy-MM-dd")Date fecha) {
        return ServiceConvertionHelper.findList(vueloRepository.findByFecha(fecha), VueloDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(vueloRepository.findByEstado(estado), VueloDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByAvion(Long avion) {
        return ServiceConvertionHelper.findList(vueloRepository.findByAvion(avion), VueloDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByRuta(Long ruta) {
        return ServiceConvertionHelper.findList(vueloRepository.findByRuta(ruta), VueloDTO.class);
    }


    @Override
    @Transactional
    public VueloDTO create(VueloDTO vuelo) {
        Vuelo vuel = MapperUtils.EntityFromDto(vuelo, Vuelo.class);
        vuel = vueloRepository.save(vuel);
        return MapperUtils.DtoFromEntity(vuel, VueloDTO.class);
    }

    @Override
    @Transactional
    public Optional<VueloDTO> update(VueloDTO vuelo, Long id) {
        if (vueloRepository.findById(id).isPresent()) {
            Vuelo vuel = MapperUtils.EntityFromDto(vuelo, Vuelo.class);
            vuel = vueloRepository.save(vuel);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(vuel, VueloDTO.class));
        } else {
            return null;
        } 
    }

}
