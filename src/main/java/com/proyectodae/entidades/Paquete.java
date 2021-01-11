/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 * @author jmait
 */
@Entity
public class Paquete {
    /** Id */
    @Id
    @Size(min=9, max=9)
    @NotBlank
    String id;
    /** Dimensiones */
    @NotBlank
    @Positive
    String dimensiones;
    /** Peso */
    @NotBlank
    @Positive
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