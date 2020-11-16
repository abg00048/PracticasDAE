/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.proyectodae;

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
    /** Precio */
    @Positive
    Float precio;
    

    public Paquete(String id, String dimensiones, String peso, Float precio) {
        
        this.id = id;
        this.dimensiones = dimensiones;
        this.peso = peso;
        this.precio = precio;
        
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

    public Float getprecio() {
        return precio;
    }
    
    
    
}