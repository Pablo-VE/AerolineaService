/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea.services;

import org.una.aerolinea.dto.AuthenticationRequest;
import org.una.aerolinea.dto.AuthenticationResponse;

/**
 *
 * @author Pablo-VE
 */
public interface IAutenticacionService {
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    
}
