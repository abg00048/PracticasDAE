/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author alvar
 */
@Configuration
public class ServicioSeguridadPaqueteria extends WebSecurityConfigurerAdapter{
    @Autowired
    ServicioDatosCliente servicioDatosCliente;
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(servicioDatosCliente)
            .passwordEncoder(new BCryptPasswordEncoder());
        
        //auth.inMemoryAuthentication()
        //        .withUser("ujacoin").roles("CLIENTE").password("{noop}secret");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        
        httpSecurity.httpBasic();
        
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/ujacoin/clientes").anonymous();
        // Permitir el acceso de un cliente sólo a sus recursos asociados (datos personales, cuentas, tarjetas, etc.)
        httpSecurity.authorizeRequests().antMatchers("/ujacoin/clientes/{dni}/**")
                .access("hasRole('CLIENTE') and #dni == principal.username");
    }
}
