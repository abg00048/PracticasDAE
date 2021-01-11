/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author alvar
 */
public class Codificador {
    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private Codificador() {
    }

    public static String codificar(String cadena) {
        return encoder.encode(cadena);
    }
    
    public static boolean igual(String password, String passwordCodificado) {
        return encoder.matches(password, passwordCodificado);
    }
}
