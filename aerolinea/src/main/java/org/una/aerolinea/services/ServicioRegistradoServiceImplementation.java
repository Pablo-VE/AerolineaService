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
import org.una.aerolinea.entities.ServicioRegistrado;
import org.una.aerolinea.dto.ServicioRegistradoDTO;
import org.una.aerolinea.repositories.IServicioRegistradoRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class ServicioRegistradoServiceImplementation implements IServicioRegistradoService{
    @Autowired
    private IServicioRegistradoRepository servicioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistradoDTO>> findAll() {
        return ServiceConvertionHelper.findList(servicioRepository.findAll(), ServicioRegistradoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioRegistradoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(servicioRepository.findById(id), ServicioRegistradoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistradoDTO>> findByCobroRango(float mas, float menos) {
        return ServiceConvertionHelper.findList(servicioRepository.findByCobroRango(mas, menos), ServicioRegistradoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistradoDTO>> findByTipoContainingIgnoreCase(String tipo) {
        return ServiceConvertionHelper.findList(servicioRepository.findByTipo(tipo), ServicioRegistradoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistradoDTO>> findByEstadoCobro(boolean estadoCobro) {
        return ServiceConvertionHelper.findList(servicioRepository.findByEstadoCobro(estadoCobro), ServicioRegistradoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioRegistradoDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(servicioRepository.findByEstado(estado), ServicioRegistradoDTO.class);
    }
    
    @Override
    public Optional<List<ServicioRegistradoDTO>> findByAvion(Long avion) {
        return ServiceConvertionHelper.findList(servicioRepository.findByAvion(avion), ServicioRegistradoDTO.class);
    }

    @Override
    @Transactional
    public ServicioRegistradoDTO create(ServicioRegistradoDTO servicioAeropuerto) {
        ServicioRegistrado servicios = MapperUtils.EntityFromDto(servicioAeropuerto, ServicioRegistrado.class);
        servicios = servicioRepository.save(servicios);
        return MapperUtils.DtoFromEntity(servicios, ServicioRegistradoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ServicioRegistradoDTO> update(ServicioRegistradoDTO servicioAeropuerto, Long id) {
        if (servicioRepository.findById(id).isPresent()) {
            ServicioRegistrado servicios = MapperUtils.EntityFromDto(servicioAeropuerto, ServicioRegistrado.class);
            servicios = servicioRepository.save(servicios);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(servicios, ServicioRegistradoDTO.class));
        } else {
            return null;
        } 
    }
    
}
