/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.loaders;

/**
 *
 * @author Luis
*/
public enum Roles {
    ROLE_gestor("gestor"),
    ROLE_gerente("gerente"),
    ROLE_administrador("administrador"),
    ROLE_auditor("auditor");
    

    
    private String nombre;

    Roles(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
