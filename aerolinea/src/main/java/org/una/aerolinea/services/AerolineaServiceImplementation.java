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
import org.una.aerolinea.entities.Aerolinea;
import org.una.aerolinea.dto.AerolineaDTO;
import org.una.aerolinea.repositories.IAerolineaRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class AerolineaServiceImplementation implements IAerolineaService {
    @Autowired
    private IAerolineaRepository aerolineaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineaDTO>> findAll() {
        return ServiceConvertionHelper.findList(aerolineaRepository.findAll(), AerolineaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AerolineaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(aerolineaRepository.findById(id), AerolineaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineaDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return ServiceConvertionHelper.findList(aerolineaRepository.findByNombreContainingIgnoreCase(nombre), AerolineaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineaDTO>> findByResponsableContainingIgnoreCase(String responsable) {
        return ServiceConvertionHelper.findList(aerolineaRepository.findByResponsableContainingIgnoreCase(responsable), AerolineaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineaDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(aerolineaRepository.findByEstado(estado), AerolineaDTO.class);
    }

    @Override
    @Transactional
    public AerolineaDTO create(AerolineaDTO aerolinea) {
        Aerolinea aeroline = MapperUtils.EntityFromDto(aerolinea, Aerolinea.class);
        aeroline = aerolineaRepository.save(aeroline);
        return MapperUtils.DtoFromEntity(aeroline, AerolineaDTO.class);
    }

    @Override
    @Transactional
    public Optional<AerolineaDTO> update(AerolineaDTO aerolinea, Long id) {
        if (aerolineaRepository.findById(id).isPresent()) {
            Aerolinea aeroline = MapperUtils.EntityFromDto(aerolinea, Aerolinea.class);
            aeroline = aerolineaRepository.save(aeroline);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(aeroline, AerolineaDTO.class));
        } else {
            return null;
        } 
    }
    
}
