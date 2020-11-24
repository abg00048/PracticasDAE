/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.excepciones;

/**
 * Excepción provocada por intentos de acceso o creación de cuentas
 * de clientes no registrados
 * @author alvar
 */
public class ClienteNoRegistrado extends RuntimeException {

    public ClienteNoRegistrado() {
    }
}
