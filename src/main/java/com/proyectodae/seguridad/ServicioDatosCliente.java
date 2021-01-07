/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.seguridad;

import com.proyectodae.entidades.Cliente;
import com.proyectodae.servicio.Paqueteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 *
 * @author jmait
 */
@Service
public class ServicioDatosCliente {
    @Autowired
    Paqueteria paqueteria;
    
    PasswordEncoder encoder;
    
    public ServicioDatosCliente() {
        encoder = new BCryptPasswordEncoder();
    }
    
    PasswordEncoder getEncoder() {
        return encoder;
    }
    
    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Cliente cliente = paqueteria.verCliente(dni);
                .orElseThrow(() -> new UsernameNotFoundException(""));
        
        return User.withUsername(cliente.getDni())
                .roles("CLIENTE").password(cliente.getClave())
                .build();
    }
}
