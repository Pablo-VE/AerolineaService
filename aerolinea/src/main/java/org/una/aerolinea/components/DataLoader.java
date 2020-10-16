/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.components;

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

        if (empleadoService.findByCedula(cedula).isEmpty()) {

            Rol rol;
            final String nombre = "Administrador"; 
            Optional<Rol> rolBuscado = rolService.findByNombre(nombre);

            if (rolBuscado.isEmpty()) { 
                rol = new Rol();
                rol.setEstado(true);
                rol.setNombre(nombre);
                rol.setDescripcion("Administrar la aplicacion");
                rol = rolService.create(rol);

            } else {
                rol = rolBuscado.get();
            }
            
            Empleado empleado = new Empleado();
            empleado.setNombre("Usuario Admin");
            empleado.setCedula(cedula);
            empleado.setEstado(true);
            empleado=empleadoService.create(empleado);
            
            Usuario usuario = new Usuario();
            usuario.setEstado(true);
            usuario.setRol(rol);
            usuario.setPasswordEncriptado(password);
            usuario.setCedula(empleado.getCedula());
            usuario.setEmpleado(empleado);
            usuario = usuarioService.create(usuario);
            
            System.out.println(usuario.toString());
            
            

            System.out.println("Se agrega el usuario inicial");
        } else {
            System.out.println("Se encontro el admin");
        }

    }
        private void createPermisos() {
        for (Roles rol : Roles.values()) {
            Rol nuevoRol = new Rol();
            nuevoRol.setNombre(rol.getNombre());
           // nuevoPermiso.setDescripcion(rol.name());
            rolService.create(nuevoRol);
        } 
    }

}
