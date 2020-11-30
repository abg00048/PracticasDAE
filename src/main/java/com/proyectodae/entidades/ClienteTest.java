/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;


/**
 *
 * @author jmait
 */
public class ClienteTest {
    
    public ClienteTest(){}
    
    @Test
    void testValidacionCliente() {
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);
        
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
                
        Assertions.assertThat(violations).isEmpty();
    }
    
    @Test
    void testComprobacionClave() {
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);
        
        Assertions.assertThat(cliente.claveValida(clave)).isTrue();
    }
}

