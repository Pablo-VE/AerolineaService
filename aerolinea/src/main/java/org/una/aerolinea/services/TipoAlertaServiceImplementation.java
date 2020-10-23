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
import org.una.aerolinea.entities.TipoAlerta;
import org.una.aerolinea.dto.TipoAlertaDTO;
import org.una.aerolinea.repositories.ITipoAlertaRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TipoAlertaServiceImplementation implements ITipoAlertaService{
    @Autowired 
    private ITipoAlertaRepository alertaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAlertaDTO>> findAll() {
        return ServiceConvertionHelper.findList(alertaRepository.findAll(), TipoAlertaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAlertaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(alertaRepository.findById(id), TipoAlertaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAlertaDTO>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return ServiceConvertionHelper.findList(alertaRepository.findByDescripcionContainingIgnoreCase(descripcion), TipoAlertaDTO.class);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoAlertaDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(alertaRepository.findByEstado(estado), TipoAlertaDTO.class);
    }
    @Override
    @Transactional
    public TipoAlertaDTO create(TipoAlertaDTO alerta) {
        TipoAlerta ro = MapperUtils.EntityFromDto(alerta, TipoAlerta.class);
        ro = alertaRepository.save(ro);
        return MapperUtils.DtoFromEntity(ro, TipoAlertaDTO.class);
    }

    @Override
    @Transactional
    public Optional<TipoAlertaDTO> update(TipoAlertaDTO alerta, Long id) {
        if (alertaRepository.findById(id).isPresent()) {
            TipoAlerta alert = MapperUtils.EntityFromDto(alerta, TipoAlerta.class);
            alert = alertaRepository.save(alert);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(alert, TipoAlertaDTO.class));
        } else {
            return null;
        } 
    }
    
}
