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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.aerolinea.dto.AuthenticationRequest;
import org.una.aerolinea.entities.Empleado;
import org.una.aerolinea.entities.Usuario;
import org.una.aerolinea.jwt.JwtProvider;
import org.una.aerolinea.repositories.IEmpleadoRepository;
import org.una.aerolinea.repositories.IUsuarioRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class UsuarioServiceImplementation implements IUsuarioService, UserDetailsService{
    
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
     @Autowired
    private JwtProvider jwtProvider;

    private void encriptarPassword(Usuario usuario) {
        String password = usuario.getPasswordEncriptado();
        if (!password.isBlank()) {
            usuario.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
    } 

    
    @Override
    public Optional<List<Usuario>> findAll() {
        return Optional.ofNullable(usuarioRepository.findAll());
    }

    @Override
    public Optional<List<Usuario>> findByEstado(boolean estado) {
        return Optional.ofNullable(usuarioRepository.findByEstado(estado));
    }

    @Override
    public Optional<List<Usuario>> findByRol(Long rol) {
        return Optional.ofNullable(usuarioRepository.findByRol(rol));
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
    
    @Override
    public Optional<Usuario> findByCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula);
    }

    @Override
    public Usuario create(Usuario usuario) {
        encriptarPassword(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            
            return Optional.ofNullable(usuarioRepository.save(usuario));
        } else {
            return null;
        }
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByCedula(username);
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ADMIN"));
            UserDetails userDetails = new User(usuario.getCedula(), usuario.getPasswordEncriptado(), roles);
            return userDetails;
        } else {
            return null;
        }

    }
    
    @Override
    public String login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authenticationRequest);
 
    }

    
}
