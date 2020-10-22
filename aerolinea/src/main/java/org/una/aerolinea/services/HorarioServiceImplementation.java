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
import org.una.aerolinea.entities.Horario;
import org.una.aerolinea.dto.HorarioDTO;
import org.una.aerolinea.repositories.IHorarioRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class HorarioServiceImplementation implements IHorarioService{
    @Autowired
    private IHorarioRepository horarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findAll() {
        return ServiceConvertionHelper.findList(horarioRepository.findAll(), HorarioDTO.class);
    }
        
    @Override
    @Transactional(readOnly = true)
    public Optional<HorarioDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(horarioRepository.findById(id), HorarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(horarioRepository.findByEstado(estado), HorarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findByEmpleado(Long empleado) {
        return ServiceConvertionHelper.findList(horarioRepository.findByEmpleado(empleado), HorarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findByEmpleadoAndEstado(Long empleado, boolean estado) {
        return ServiceConvertionHelper.findList(horarioRepository.findByEmpleadoAndEstado(empleado, estado), HorarioDTO.class);
    }

    @Override
    @Transactional
    public HorarioDTO create(HorarioDTO horario) {
        Horario hora = MapperUtils.EntityFromDto(horario, Horario.class);
        hora = horarioRepository.save(hora);
        return MapperUtils.DtoFromEntity(hora, HorarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<HorarioDTO> update(HorarioDTO horario, Long id) {
        if (horarioRepository.findById(id).isPresent()) {
            Horario hora = MapperUtils.EntityFromDto(horario, Horario.class);
            hora = horarioRepository.save(hora);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(hora, HorarioDTO.class));
        } else {
            return null;
        } 
    }
    
}
