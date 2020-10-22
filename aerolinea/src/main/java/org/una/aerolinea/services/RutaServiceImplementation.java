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
import org.una.aerolinea.entities.Ruta;
import org.una.aerolinea.dto.RutaDTO;
import org.una.aerolinea.repositories.IRutaRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class RutaServiceImplementation implements IRutaService{
    @Autowired
    private IRutaRepository rutaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RutaDTO>> findAll() {
        return ServiceConvertionHelper.findList(rutaRepository.findAll(), RutaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RutaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(rutaRepository.findById(id), RutaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RutaDTO>> findByOrigenContainingIgnoreCase(String origen) {
        return ServiceConvertionHelper.findList(rutaRepository.findByOrigenContainingIgnoreCase(origen), RutaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RutaDTO>> findByDestinoContainingIgnoreCase(String destino) {
        return ServiceConvertionHelper.findList(rutaRepository.findByDestinoContainingIgnoreCase(destino), RutaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RutaDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(rutaRepository.findByEstado(estado), RutaDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<RutaDTO>> findByDistanciaRango(float distanciaMaxima, float distanciaMinima) {
        return ServiceConvertionHelper.findList(rutaRepository.findByDistanciaRango(distanciaMaxima, distanciaMinima), RutaDTO.class);
    }

    @Override
    @Transactional
    public RutaDTO create(RutaDTO ruta) {
        Ruta rut = MapperUtils.EntityFromDto(ruta, Ruta.class);
        rut = rutaRepository.save(rut);
        return MapperUtils.DtoFromEntity(rut, RutaDTO.class);
    }

    @Override
    @Transactional
    public Optional<RutaDTO> update(RutaDTO ruta, Long id) {
        if (rutaRepository.findById(id).isPresent()) {
            Ruta rut = MapperUtils.EntityFromDto(ruta, Ruta.class);
            rut = rutaRepository.save(rut);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(rut, RutaDTO.class));
        } else {
            return null;
        } 
    }

    
    
}
