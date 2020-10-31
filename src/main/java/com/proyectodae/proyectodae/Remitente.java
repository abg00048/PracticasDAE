/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.proyectodae;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author alvar
 */
public class Remitente extends Cliente{
    /** Identificador del cliente remitente */
    @NotBlank
    String idRemitente;   
    
    public Remitente(String dni, String nombre, LocalDate fNacimiento, String direccion, String tlf, String email, String clave, String idRemitente) {
        super(dni, nombre, fNacimiento, direccion, tlf, email, clave);
        this.idRemitente = idRemitente;
    }
    
    public String getIdRemitente() {
        return idRemitente;
    }
    
}
