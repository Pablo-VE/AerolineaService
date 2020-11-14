/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aerolinea.dto.UsuarioDTO;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.entities.Usuario;
import org.una.aerolinea.jwt.JwtProvider;
import org.una.aerolinea.repositories.IEmpleadoRepository;
import org.una.aerolinea.repositories.IRolRepository;
import org.una.aerolinea.repositories.IUsuarioRepository;
import org.una.aerolinea.utils.MapperUtils;
import org.una.aerolinea.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class UsuarioServiceImplementation implements IUsuarioService, UserDetailsService{
    
    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
     @Autowired
    private JwtProvider jwtProvider;

     
    private UsuarioDTO encriptarPassword(UsuarioDTO usuario) {
        String password = usuario.getPasswordEncriptado();
        if (!password.isBlank()) {
            usuario.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
        return usuario;
    }


    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findAll() {
        return ServiceConvertionHelper.findList(usuarioRepository.findAll(), UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(usuarioRepository.findByEstado(estado), UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByRol(Long rol) {
        return ServiceConvertionHelper.findList(usuarioRepository.findByRol(rol), UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
          return ServiceConvertionHelper.oneToOptionalDto(usuarioRepository.findById(id), UsuarioDTO.class);
    }

    
    @Override
    public Optional<List<UsuarioDTO>> findByCedulaEmpleado(String cedulaEmpleado) {
        return ServiceConvertionHelper.findList(usuarioRepository.findByCedulaEmpleado(cedulaEmpleado), UsuarioDTO.class);
    }

    @Override
    public Optional<List<UsuarioDTO>> findByNombreEmpleado(String nombreEmpleado) {
        return ServiceConvertionHelper.findList(usuarioRepository.findByNombreEmpleado(nombreEmpleado), UsuarioDTO.class);
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCedula(String cedula) {
        return ServiceConvertionHelper.oneToOptionalDto(usuarioRepository.findByCedula(cedula), UsuarioDTO.class);
    }

    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        usuarioDTO = encriptarPassword(usuarioDTO);
        Usuario usuario = MapperUtils.EntityFromDto(usuarioDTO, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        return MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class);
    }


    @Override
    @Transactional
    public Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            usuarioDTO = encriptarPassword(usuarioDTO);
            Usuario usuario = MapperUtils.EntityFromDto(usuarioDTO, Usuario.class);
            usuario = usuarioRepository.save(usuario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class));
        } else {
            return null;
        } 
    }

    
    @Autowired
    private IRolRepository rolRepository;
        

    @Override
    @Transactional(readOnly = true) 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Rol> roless = rolRepository.findByEstado(true);
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByCedula(username);
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            for(int i=0; i<roless.size(); i++){
                roles.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRol().getNombre()));
            }
            UserDetails userDetails = new User(usuario.getEmpleado().getCedula(), usuario.getPasswordEncriptado(), roles);
            return userDetails;
        } else {
            return null;
        }

    }


    
}
