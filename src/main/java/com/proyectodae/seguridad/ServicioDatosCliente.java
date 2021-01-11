/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.seguridad;

import com.proyectodae.entidades.Cliente;
import com.proyectodae.servicio.Paqueteria;
<<<<<<< Updated upstream
=======
import org.springframework.stereotype.Service;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< Updated upstream

/**
 *
 * @author jmait
 */
@Service
public class ServicioDatosCliente {
    @Autowired
    Paqueteria paqueteria;
=======
/**
 *
 * @author alvar
 */
@Service
public class ServicioDatosCliente implements UserDetailsService{
    @Autowired
    Paqueteria servicioPaqueteria;
>>>>>>> Stashed changes
    
    PasswordEncoder encoder;
    
    public ServicioDatosCliente() {
        encoder = new BCryptPasswordEncoder();
    }
    
    PasswordEncoder getEncoder() {
        return encoder;
    }
    
    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
<<<<<<< Updated upstream
        Cliente cliente = paqueteria.verCliente(dni);
=======
        Cliente cliente = servicioPaqueteria.verCliente(dni)
>>>>>>> Stashed changes
                .orElseThrow(() -> new UsernameNotFoundException(""));
        
        return User.withUsername(cliente.getDni())
                .roles("CLIENTE").password(cliente.getClave())
                .build();
    }
}
