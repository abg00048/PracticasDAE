/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

/**
 *
 * @author raulb
 */
public class PuntoControl {
    /**
     * Número de identificación del punto de control
     */
    private String id;
    
    public PuntoControl(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
    
}
