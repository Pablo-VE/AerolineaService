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
import org.una.aerolinea.entities.TrabajoEmpleado;
import org.una.aerolinea.dto.TrabajoEmpleadoDTO;
import org.una.aerolinea.repositories.ITrabajoEmpleadoRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TrabajoEmpleadoServiceImplementation implements ITrabajoEmpleadoService{
    @Autowired
    private ITrabajoEmpleadoRepository trabajoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(trabajoRepository.findAll(), TrabajoEmpleadoDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TrabajoEmpleadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(trabajoRepository.findById(id), TrabajoEmpleadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleadoDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(trabajoRepository.findByEstado(estado), TrabajoEmpleadoDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleadoDTO>> findByEmpleado(Long empleado) {
        return ServiceConvertionHelper.findList(trabajoRepository.findByEmpleado(empleado), TrabajoEmpleadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TrabajoEmpleadoDTO>> findByAreaTrabajo(Long areaTrabajo) {
        return ServiceConvertionHelper.findList(trabajoRepository.findByAreaTrabajo(areaTrabajo), TrabajoEmpleadoDTO.class);
    }

    @Override
    @Transactional
    public TrabajoEmpleadoDTO create(TrabajoEmpleadoDTO trabajoEmpleado) {
        TrabajoEmpleado trabajoEm = MapperUtils.EntityFromDto(trabajoEmpleado, TrabajoEmpleado.class);
        trabajoEm = trabajoRepository.save(trabajoEm);
        return MapperUtils.DtoFromEntity(trabajoEm, TrabajoEmpleadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TrabajoEmpleadoDTO> update(TrabajoEmpleadoDTO trabajoEmpleado, Long id) {
        if (trabajoRepository.findById(id).isPresent()) {
            TrabajoEmpleado trabajoEm = MapperUtils.EntityFromDto(trabajoEmpleado, TrabajoEmpleado.class);
            trabajoEm = trabajoRepository.save(trabajoEm);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(trabajoEm, TrabajoEmpleadoDTO.class));
        } else {
            return null;
        } 
    }
    
    
}
