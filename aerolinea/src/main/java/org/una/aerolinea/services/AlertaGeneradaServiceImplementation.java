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
import org.una.aerolinea.entities.AlertaGenerada;
import org.una.aerolinea.dto.AlertaGeneradaDTO;
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.repositories.IAlertaGeneradaRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Luis
 */
@Service
public class AlertaGeneradaServiceImplementation implements IAlertaGeneradaService{

    @Autowired
    private IAlertaGeneradaRepository alertaGeneradaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGeneradaDTO>> findAll() {
        return ServiceConvertionHelper.findList(alertaGeneradaRepository.findAll(), AlertaGeneradaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertaGeneradaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(alertaGeneradaRepository.findById(id), AlertaGeneradaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGeneradaDTO>> findByAutorizacion(String autorizacion) {
        return ServiceConvertionHelper.findList(alertaGeneradaRepository.findByAutorizacion(autorizacion), AlertaGeneradaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGeneradaDTO>> findByEstado(boolean estado) {
         return ServiceConvertionHelper.findList(alertaGeneradaRepository.findByEstado(estado), AlertaGeneradaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGeneradaDTO>> findByTipoAlerta(Long tipoAlerta) {
         return ServiceConvertionHelper.findList(alertaGeneradaRepository.findByTipoAlertaSQL(tipoAlerta), AlertaGeneradaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaGeneradaDTO>> findByVuelo(Long vuelo) {
         return ServiceConvertionHelper.findList(alertaGeneradaRepository.findByVueloSQL(vuelo), AlertaGeneradaDTO.class);
    }

    @Override
    @Transactional
    public AlertaGeneradaDTO create(AlertaGeneradaDTO alertaGenerada) {
        AlertaGenerada alerta = MapperUtils.EntityFromDto(alertaGenerada, AlertaGenerada.class);
        alerta = alertaGeneradaRepository.save(alerta);
        return MapperUtils.DtoFromEntity(alerta, AlertaGeneradaDTO.class);
    }


    @Override
    @Transactional
    public Optional<AlertaGeneradaDTO> update(AlertaGeneradaDTO alertaGenerada, Long id) {
        if (alertaGeneradaRepository.findById(id).isPresent()) {
            AlertaGenerada alerta = MapperUtils.EntityFromDto(alertaGenerada, AlertaGenerada.class);
            alerta = alertaGeneradaRepository.save(alerta);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(alerta, AlertaGeneradaDTO.class));
        } else {
            return null;
        } 
    }
    
    
    
    
}
