/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.proyectodae;

import com.proyectodae.util.Codificador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

/**
 *
 * @author alvar
 */
public class Cliente {
    /** Nombre completo */
    @NotBlank
    String nombre;
    /** Dirección del domicilio */
    @NotBlank
    String direccion;
    /** Teléfono */
    @Pattern(regexp="^(\\+34|0034|34)?[6789]\\d{8}$")
    String tlf;
    
    /** Email */
    @Email
    String email;
    /** Clave de acceso al sistema */
    String clave;

    public Cliente(String dni, String nombre, LocalDate fNacimiento, String direccion, String tlf, String email, String clave) {
        
        this.nombre = nombre;
        this.direccion = direccion;
        this.tlf = tlf;
        this.email = email;
        this.clave = Codificador.codificar(clave);
        
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
    
    /**
     * Compara la clave con la del cliente, codificándola en Md5
     * @param clave
     * @return 
     */
    public boolean claveValida(String clave) {
        return this.clave.equals(Codificador.codificar(clave));        
    }
    
}
