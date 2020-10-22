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
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.repositories.IRolRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class RolServiceImplementation implements IRolService {
    @Autowired
    private IRolRepository rolRepositoy;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findAll() {
        return ServiceConvertionHelper.findList(rolRepositoy.findAll(), RolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(rolRepositoy.findById(id), RolDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByNombre(String nombre) {
        return ServiceConvertionHelper.findList(rolRepositoy.findByNombre(nombre), RolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByNombreContainingIgnoreCase(String nombre) {
        return ServiceConvertionHelper.findList(rolRepositoy.findByNombreContainingIgnoreCase(nombre), RolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByDescripcionContainingIgnoreCase(String descripcion) {
         return ServiceConvertionHelper.findList(rolRepositoy.findByDescripcionContainingIgnoreCase(descripcion), RolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(rolRepositoy.findByEstado(estado), RolDTO.class);
    }

    @Override
    @Transactional
    public RolDTO create(RolDTO rol) {
        Rol ro = MapperUtils.EntityFromDto(rol, Rol.class);
        ro = rolRepositoy.save(ro);
        return MapperUtils.DtoFromEntity(ro, RolDTO.class);
    }

    @Override
    @Transactional
    public Optional<RolDTO> update(RolDTO rol, Long id) {
        if (rolRepositoy.findById(id).isPresent()) {
            Rol user = MapperUtils.EntityFromDto(rol, Rol.class);
            user = rolRepositoy.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, RolDTO.class));
        } else {
            return null;
        } 
    }
    
}
