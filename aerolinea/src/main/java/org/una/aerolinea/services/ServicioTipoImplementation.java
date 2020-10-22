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
import org.una.aerolinea.entities.ServicioTipo;
import org.una.aerolinea.dto.ServicioTipoDTO;
import org.una.aerolinea.repositories.IServicioTipoRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Luis
 */
@Service
public class ServicioTipoImplementation implements IServicioTipoService{
   
    @Autowired
    private IServicioTipoRepository tipoServicioRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipoDTO>> findAll() {
        return ServiceConvertionHelper.findList(tipoServicioRepository.findAll(), ServicioTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioTipoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(tipoServicioRepository.findById(id), ServicioTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipoDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return ServiceConvertionHelper.findList(tipoServicioRepository.findByNombreContainingIgnoreCase(nombre), ServicioTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipoDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(tipoServicioRepository.findByEstado(estado), ServicioTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioTipoDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(tipoServicioRepository.findByDescripcion(descripcion), ServicioTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ServicioTipoDTO create(ServicioTipoDTO tipoServicioAeropuerto) {
        ServicioTipo tipoServicio = MapperUtils.EntityFromDto(tipoServicioAeropuerto, ServicioTipo.class);
        tipoServicio = tipoServicioRepository.save(tipoServicio);
        return MapperUtils.DtoFromEntity(tipoServicio, ServicioTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioTipoDTO> update(ServicioTipoDTO tipoServicioAeropuerto, Long id) {
        if (tipoServicioRepository.findById(id).isPresent()) {
            ServicioTipo tipoServicio = MapperUtils.EntityFromDto(tipoServicioAeropuerto, ServicioTipo.class);
            tipoServicio = tipoServicioRepository.save(tipoServicio);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tipoServicio, ServicioTipoDTO.class));
        } else {
            return null;
        } 
    }
}
