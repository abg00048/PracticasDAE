/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.excepciones;

/**
 * Excepcion producida por intento de registro de clientes ya existentes
 * @author alvar
 */
public class ClienteYaRegistrado extends RuntimeException {
    
    public ClienteYaRegistrado() {
    }  
}
