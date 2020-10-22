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
import org.una.aerolinea.dto.AreaTrabajoDTO;
import org.una.aerolinea.entities.AreaTrabajo;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.repositories.IAreaTrabajoRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {
    @Autowired
    private IAreaTrabajoRepository atrabajoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findAll() {
        return ServiceConvertionHelper.findList(atrabajoRepository.findAll(), AreaTrabajoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(atrabajoRepository.findById(id), AreaTrabajoDTO.class);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return ServiceConvertionHelper.findList(atrabajoRepository.findByNombreContainingIgnoreCase(nombre), AreaTrabajoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findByDescripcionContainingIgnoreCase(String descripcion) {
         return ServiceConvertionHelper.findList(atrabajoRepository.findByDescripcionContainingIgnoreCase(descripcion), AreaTrabajoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findByEstado(boolean estado) {
         return ServiceConvertionHelper.findList(atrabajoRepository.findByEstado(estado), AreaTrabajoDTO.class);
    }

    @Override
    @Transactional
    public AreaTrabajoDTO create(AreaTrabajoDTO areaTrabajo) {
        AreaTrabajo areaT = MapperUtils.EntityFromDto(areaTrabajo, AreaTrabajo.class);
        areaT = atrabajoRepository.save(areaT);
        return MapperUtils.DtoFromEntity(areaT, AreaTrabajoDTO.class);
    }
    
    @Override
    @Transactional
    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO areaTrabajo, Long id) {
        if (atrabajoRepository.findById(id).isPresent()) {
            AreaTrabajo areaT = MapperUtils.EntityFromDto(areaTrabajo, AreaTrabajo.class);
            areaT = atrabajoRepository.save(areaT);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(areaT, AreaTrabajoDTO.class));
        } else {
            return null;
        } 
    }
}
