/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import java.util.ArrayList;

/**
 *
 * @author raulb
 */
public class RegistroEnvio {
    
    /**
     * Puntos de control por donde pasa el envío
     */
    private ArrayList<PuntoControl> puntoscontrol;

    public RegistroEnvio(){
        this.puntoscontrol = new ArrayList<>();
    }

    public ArrayList<PuntoControl> getPuntoscontrol() {
        return puntoscontrol;
    }

    public void setPuntoscontrol(ArrayList<PuntoControl> puntoscontrol) {
        this.puntoscontrol = puntoscontrol;
    }   
    
}
