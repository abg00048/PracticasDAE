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
public class Destinatario extends Cliente{
    /** Identificador del cliente destinatario */
    @NotBlank
    String idDestinatario;

    public Destinatario(String dni, String nombre, LocalDate fNacimiento, String direccion, String tlf, String email, String clave, String idDestinatario) {
        super(dni, nombre, fNacimiento, direccion, tlf, email, clave);
        this.idDestinatario = idDestinatario;
    }

    public String getIdDestinatario() {
        return idDestinatario;
    }
    
}
