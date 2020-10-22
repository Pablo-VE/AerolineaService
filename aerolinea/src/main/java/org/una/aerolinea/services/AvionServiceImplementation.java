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
import org.una.aerolinea.dto.AvionDTO;
import org.una.aerolinea.entities.Avion;
import org.una.aerolinea.repositories.IAvionRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class AvionServiceImplementation implements IAvionService{
    @Autowired
    private IAvionRepository avionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionDTO>> findAll() {
        return ServiceConvertionHelper.findList(avionRepository.findAll(), AvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AvionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(avionRepository.findById(id), AvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionDTO>> findByMatricula(String matricula) {
        return ServiceConvertionHelper.findList(avionRepository.findByMatricula(matricula), AvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionDTO>> findByMatriculaContainingIgnoreCase(String matricula) {
        return ServiceConvertionHelper.findList(avionRepository.findByMatriculaContainingIgnoreCase(matricula), AvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(avionRepository.findByEstado(estado), AvionDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionDTO>> findByAerolinea(Long aerolinea) {
        return ServiceConvertionHelper.findList(avionRepository.findByAerolinea(aerolinea), AvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AvionDTO>> findByTipoAvion(Long tipoAvion) {
        return ServiceConvertionHelper.findList(avionRepository.findByTipoAvion(tipoAvion), AvionDTO.class);
    }

    @Override
    @Transactional
    public AvionDTO create(AvionDTO avion) {
        Avion avio = MapperUtils.EntityFromDto(avion, Avion.class);
        avio = avionRepository.save(avio);
        return MapperUtils.DtoFromEntity(avio, AvionDTO.class);
    }

    @Override
    @Transactional
    public Optional<AvionDTO> update(AvionDTO avion, Long id) {
        if (avionRepository.findById(id).isPresent()) {
            Avion avio = MapperUtils.EntityFromDto(avion, Avion.class);
            avio = avionRepository.save(avio);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(avio, AvionDTO.class));
        } else {
            return null;
        } 
    }
   
}
