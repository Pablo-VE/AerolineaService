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
import org.una.aerolinea.entities.BitacoraAvion;
import org.una.aerolinea.dto.BitacoraAvionDTO;
import org.una.aerolinea.repositories.IBitacoraAvionRepository;

import org.springframework.transaction.annotation.Transactional;
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;



/**
 *
 * @author Luis
 */
@Service
public class BitacoraAvionServiceImplementation implements IBitacoraAvionService{

    @Autowired
    private IBitacoraAvionRepository bitacoraAvionRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvionDTO>> findAll() {
        return ServiceConvertionHelper.findList(bitacoraAvionRepository.findAll(), BitacoraAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BitacoraAvionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(bitacoraAvionRepository.findById(id), BitacoraAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvionDTO>> findByCombustible(int combustible) {
        return ServiceConvertionHelper.findList(bitacoraAvionRepository.findByCombustible(combustible), BitacoraAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvionDTO>> findByDistanciaRecorrida(int distanciaRec) {
        return ServiceConvertionHelper.findList(bitacoraAvionRepository.findByDistanciaRecorrida(distanciaRec), BitacoraAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvionDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(bitacoraAvionRepository.findByEstado(estado), BitacoraAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvionDTO>> findByTiempoTierra(int tiempoTierra) {
        return ServiceConvertionHelper.findList(bitacoraAvionRepository.findByTiempoTierra(tiempoTierra), BitacoraAvionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<BitacoraAvionDTO>> findByUbicacionContainingIgnoreCase(String ubicacion) {
        return ServiceConvertionHelper.findList(bitacoraAvionRepository.findByUbicacionContainingIgnoreCase(ubicacion), BitacoraAvionDTO.class);
    }

    @Override
    @Transactional
    public BitacoraAvionDTO create(BitacoraAvionDTO avionEstado) {
        BitacoraAvion bitacoraAvion = MapperUtils.EntityFromDto(avionEstado, BitacoraAvion.class);
        bitacoraAvion = bitacoraAvionRepository.save(bitacoraAvion);
        return MapperUtils.DtoFromEntity(bitacoraAvion, BitacoraAvionDTO.class);
    }

    @Override
    @Transactional
    public Optional<BitacoraAvionDTO> update(BitacoraAvionDTO avionEstado, Long id) {
        if (bitacoraAvionRepository.findById(id).isPresent()) {
            BitacoraAvion bitacoraAvion = MapperUtils.EntityFromDto(avionEstado, BitacoraAvion.class);
            bitacoraAvion = bitacoraAvionRepository.save(bitacoraAvion);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(bitacoraAvion, BitacoraAvionDTO.class));
        } else {
            return null;
        } 
    }
    
}
