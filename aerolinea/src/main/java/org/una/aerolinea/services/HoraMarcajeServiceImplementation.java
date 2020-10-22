/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aerolinea.entities.HoraMarcaje;
import org.una.aerolinea.dto.HoraMarcajeDTO;
import org.una.aerolinea.repositories.IHoraMarcajeRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class HoraMarcajeServiceImplementation implements IHoraMarcajeService{
    @Autowired
    private IHoraMarcajeRepository horaMarcajeRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcajeDTO>> findAll() {
        return ServiceConvertionHelper.findList(horaMarcajeRepository.findAll(), HoraMarcajeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HoraMarcajeDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(horaMarcajeRepository.findById(id), HoraMarcajeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcajeDTO>> findByEmpleado(Long empleado) {
        return ServiceConvertionHelper.findList(horaMarcajeRepository.findByEmpleado(empleado), HoraMarcajeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcajeDTO>> findByTipo(int tipo) {
        return ServiceConvertionHelper.findList(horaMarcajeRepository.findByTipo(tipo), HoraMarcajeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcajeDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(horaMarcajeRepository.findByEstado(estado), HoraMarcajeDTO.class);
    }

    @Override
    @Transactional
    public HoraMarcajeDTO create(HoraMarcajeDTO horaMarcaje) {
        HoraMarcaje hora = MapperUtils.EntityFromDto(horaMarcaje, HoraMarcaje.class);
        hora = horaMarcajeRepository.save(hora);
        return MapperUtils.DtoFromEntity(hora, HoraMarcajeDTO.class);
    }

    @Override
    @Transactional
    public Optional<HoraMarcajeDTO> update(HoraMarcajeDTO horaMarcaje, Long id) {
        if (horaMarcajeRepository.findById(id).isPresent()) {
            HoraMarcaje hora = MapperUtils.EntityFromDto(horaMarcaje, HoraMarcaje.class);
            hora = horaMarcajeRepository.save(hora);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(hora, HoraMarcajeDTO.class));
        } else {
            return null;
        } 
    }
    
}
