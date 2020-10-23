/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.components;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.aerolinea.entities.Empleado;
import org.una.aerolinea.entities.Rol;
import org.una.aerolinea.entities.Usuario;
import org.una.aerolinea.loaders.Roles;
import org.una.aerolinea.services.IEmpleadoService;
import org.una.aerolinea.services.IRolService;
import org.una.aerolinea.services.IUsuarioService;
import org.una.aerolinea.dto.RolDTO;
import org.una.aerolinea.dto.UsuarioDTO;
import org.una.aerolinea.dto.EmpleadoDTO;
/**
 *
 * @author Pablo-VE
 */
@Component
public class DataLoader implements ApplicationRunner{

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IEmpleadoService empleadoService;
    
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Override
   public void run(ApplicationArguments args) {

        if (usuarioService.findByCedula(cedula) == null) {

            crearRoles();
            
            RolDTO rol;
            
            Optional<RolDTO> Rol1 = rolService.findById(Long.valueOf(1));
            rol = Rol1.get();
            
            UsuarioDTO usuario = new UsuarioDTO();
            EmpleadoDTO empleado = new EmpleadoDTO();
            
            empleado.setNombre("Usuario");
            empleado.setCedula(cedula);
            
            EmpleadoDTO emp = empleadoService.create(empleado);
            usuario.setRol(rol);
            usuario.setPasswordEncriptado(password);
            usuario.setEmpleado(emp);
            usuarioService.create(usuario);

            System.out.println("Se agrega el usuario inicial");
        } else {
            System.out.println("Se encontro el admin");
        }

    }
   
        private void crearRoles(){
        for(Roles rol : Roles.values()){
            RolDTO rolDto = new RolDTO();
            rolDto.setNombre(rol.getNombre());
            if(!rolService.findAll().isPresent()){
                rolService.create(rolDto);
                System.out.println("Rol creado");
            }else{
                System.out.println("Rol encontrado");
            }
        }
    }

    

}
