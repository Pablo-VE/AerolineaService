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
import org.una.aerolinea.entities.ParametroAplicacion;
import org.una.aerolinea.dto.ParametroAplicacionDTO;
import org.una.aerolinea.repositories.IParametroAplicacionRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class ParametroAplicacionServiceImplementation implements IParametroAplicacionService{
    @Autowired
    private IParametroAplicacionRepository paramentroRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacionDTO>> findAll() {
        return ServiceConvertionHelper.findList(paramentroRepository.findAll(), ParametroAplicacionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroAplicacionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(paramentroRepository.findById(id), ParametroAplicacionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacionDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return ServiceConvertionHelper.findList(paramentroRepository.findByNombreContainingIgnoreCase(nombre), ParametroAplicacionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacionDTO>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return ServiceConvertionHelper.findList(paramentroRepository.findByDescripcionContainingIgnoreCase(descripcion), ParametroAplicacionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroAplicacionDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(paramentroRepository.findByEstado(estado), ParametroAplicacionDTO.class);
    }

    @Override
    @Transactional
    public ParametroAplicacionDTO create(ParametroAplicacionDTO parametroAplicacion) {
        ParametroAplicacion parametro = MapperUtils.EntityFromDto(parametroAplicacion, ParametroAplicacion.class);
        parametro = paramentroRepository.save(parametro);
        return MapperUtils.DtoFromEntity(parametro, ParametroAplicacionDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametroAplicacionDTO> update(ParametroAplicacionDTO parametroAplicacion, Long id) {
        if (paramentroRepository.findById(id).isPresent()) {
            ParametroAplicacion parametro = MapperUtils.EntityFromDto(parametroAplicacion, ParametroAplicacion.class);
            parametro = paramentroRepository.save(parametro);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(parametro, ParametroAplicacionDTO.class));
        } else {
            return null;
        } 
    }
    
}
