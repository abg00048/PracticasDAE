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
public class Oficina extends PuntoControl{
    
    /**
     * Localidad donde se encuentra la oficina
     */
    private String localidad;
    
    public Oficina(String localidad) {
        this.localidad = localidad;
    }
    
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    
    /**
     * Crea una entrada de envío a partir de la medida y el peso del paquete
     * 
     * @param medida Medida del paquete
     * @param peso Peso del paquete
     */
    public void CrearEnvio(float medida, float peso){
        
    }
    
    /**
     * Calcula los puntos de control por donde tiene que pasar el envío
     * 
     * @param origen Punto de control inicial
     * @param destino Punto de control final
     * @return 
     */
    public ArrayList<PuntoControl> calcularRuta(String origen, String destino){
        
    }
}
