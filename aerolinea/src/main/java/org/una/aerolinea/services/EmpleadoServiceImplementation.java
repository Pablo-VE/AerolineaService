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
import org.una.aerolinea.entities.Empleado;
import org.una.aerolinea.dto.EmpleadoDTO;
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.repositories.IEmpleadoRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */

@Service
public class EmpleadoServiceImplementation implements IEmpleadoService{
    
    @Autowired
    private IEmpleadoRepository empleadoRepositoy;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(empleadoRepositoy.findAll(), EmpleadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepositoy.findById(id), EmpleadoDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoDTO> findByCedula(String cedula) {
          return ServiceConvertionHelper.oneToOptionalDto(empleadoRepositoy.findByCedula(cedula), EmpleadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadoDTO>> findByCedulaContaining(String cedula) {
       return ServiceConvertionHelper.findList(empleadoRepositoy.findByCedulaContaining(cedula), EmpleadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadoDTO>> findByNombreContainingIgnoreCase(String nombre) {
       return ServiceConvertionHelper.findList(empleadoRepositoy.findByNombreContainingIgnoreCase(nombre), EmpleadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadoDTO>> findByEstado(boolean estado) {
       return ServiceConvertionHelper.findList(empleadoRepositoy.findByEstado(estado), EmpleadoDTO.class);
    }

    @Override
    @Transactional
    public EmpleadoDTO create(EmpleadoDTO empleado) {
        Empleado emple = MapperUtils.EntityFromDto(empleado, Empleado.class);
        emple = empleadoRepositoy.save(emple);
        return MapperUtils.DtoFromEntity(emple, EmpleadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<EmpleadoDTO> update(EmpleadoDTO empleado, Long id) {
        if (empleadoRepositoy.findById(id).isPresent()) {
            Empleado emple = MapperUtils.EntityFromDto(empleado, Empleado.class);
            emple = empleadoRepositoy.save(emple);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(emple, EmpleadoDTO.class));
        } else {
            return null;
        } 
    }
    
}
