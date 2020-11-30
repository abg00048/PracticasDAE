/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 *
 * @author jmait
 */
public class Paquete {
    /** Id */
    @NotBlank
    String id;
    /** Dimensiones */
    @NotBlank
    String dimensiones;
    /** Peso */
    @NotBlank
    String peso;
    

    public Paquete(String id, String dimensiones, String peso) {
        
        this.id = id;
        this.dimensiones = dimensiones;
        this.peso = peso;
    }

    public String getid() {
        return id;
    }

    public String getdimensiones() {
        return dimensiones;
    }

    public String getpeso() {
        return peso;
    }
    
    
}