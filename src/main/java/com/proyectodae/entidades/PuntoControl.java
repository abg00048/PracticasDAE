/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import com.proyectodae.util.RandomString;
/**
 *
 * @author raulb
 */
public class PuntoControl {
    /**
     * Número de identificación del punto de control
     */
    private String id;
    
    public PuntoControl(){
        RandomString rd = new RandomString();
        this.id = rd.generateUniqueId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
    
}
