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
import org.una.aerolinea.entities.Transaccion;
import org.una.aerolinea.dto.TransaccionDTO;
import org.una.aerolinea.repositories.ITransaccionRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TransaccionServiceImplementation implements ITransaccionService {
    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findAll() {
        return ServiceConvertionHelper.findList(transaccionRepository.findAll(), TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(transaccionRepository.findById(id), TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByLugarContainingIgnoreCase(String lugar) {
        return ServiceConvertionHelper.findList(transaccionRepository.findByLugarContainingIgnoreCase(lugar), TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByDescripcionContainingIgnoreCase(String descripcion) {
        return ServiceConvertionHelper.findList(transaccionRepository.findByDescripcionContainingIgnoreCase(descripcion), TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(transaccionRepository.findByEstado(estado), TransaccionDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByRol(String rol) {
        return ServiceConvertionHelper.findList(transaccionRepository.findByRolContainingIgnoreCase(rol), TransaccionDTO.class);
    }

    @Override
    @Transactional
    public TransaccionDTO create(TransaccionDTO transaccion) {
        Transaccion trans = MapperUtils.EntityFromDto(transaccion, Transaccion.class);
        trans = transaccionRepository.save(trans);
        return MapperUtils.DtoFromEntity(trans, TransaccionDTO.class);
    }

    @Override
    @Transactional
    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id) {
        if (transaccionRepository.findById(id).isPresent()) {
            Transaccion trans = MapperUtils.EntityFromDto(transaccion, Transaccion.class);
            trans = transaccionRepository.save(trans);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(trans, TransaccionDTO.class));
        } else {
            return null;
        } 
    }
    
}
