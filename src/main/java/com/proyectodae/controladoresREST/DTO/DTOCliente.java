/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.controladoresREST.DTO;

import com.proyectodae.entidades.Cliente;
import java.time.LocalDate;

/**
 * DTO para recopilación de datos de cliente
 * @author alvar
 */
public class DTOCliente {
    /** DNI del cliente*/
    String dni;
    /** Nombre completo */
    String nombre;
    
    /** Fecha de nacimiento */
    LocalDate fNacimiento;
    /** Dirección del domicilio */
    String direccion;
    /** Teléfono */
    String tlf;
    
    /** Email */
    String email;
    /** Clave de acceso al sistema */
    String clave;    

    public DTOCliente(String dni, String nombre, String direccion, String tlf, String email, String clave) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tlf = tlf;
        this.email = email;
        this.clave = clave;
    }

    public DTOCliente(Cliente cliente) {
        this.dni = cliente.getDni();
        this.nombre = cliente.getNombre();
        this.direccion = cliente.getDireccion();
        this.tlf = cliente.getTlf();
        this.email = cliente.getEmail();
        this.clave = "";
    }    
    
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTlf() {
        return tlf;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }
     
    public Cliente aCliente() {
        return new Cliente(dni, nombre, direccion, tlf, email, clave);
    }
}
