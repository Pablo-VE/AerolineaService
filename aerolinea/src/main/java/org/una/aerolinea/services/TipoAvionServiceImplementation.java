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
import org.una.aerolinea.entities.TipoAvion;
import org.una.aerolinea.dto.TipoAvionDTO;
import org.una.aerolinea.repositories.ITipoAvionRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TipoAvionServiceImplementation implements ITipoAvionService{
    @Autowired
    private ITipoAvionRepository tipoAvionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvionDTO>> findAll() {
        return ServiceConvertionHelper.findList(tipoAvionRepository.findAll(), TipoAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAvionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(tipoAvionRepository.findById(id), TipoAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvionDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return ServiceConvertionHelper.findList(tipoAvionRepository.findByNombreContainingIgnoreCase(nombre), TipoAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvionDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(tipoAvionRepository.findByEstado(estado), TipoAvionDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAvionDTO>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima) {
        return ServiceConvertionHelper.findList(tipoAvionRepository.findByDistanciaRango(distanciaMaxima, distanciaMinima), TipoAvionDTO.class);
    }

    @Override
    @Transactional
    public TipoAvionDTO create(TipoAvionDTO tipoAvion) {
        TipoAvion avion = MapperUtils.EntityFromDto(tipoAvion, TipoAvion.class);
        avion = tipoAvionRepository.save(avion);
        return MapperUtils.DtoFromEntity(avion, TipoAvionDTO.class);
    }

    @Override
    @Transactional
    public Optional<TipoAvionDTO> update(TipoAvionDTO tipoAvion, Long id) {
        if (tipoAvionRepository.findById(id).isPresent()) {
            TipoAvion avion = MapperUtils.EntityFromDto(tipoAvion, TipoAvion.class);
            avion = tipoAvionRepository.save(avion);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(avion, TipoAvionDTO.class));
        } else {
            return null;
        } 
    }

    
}
