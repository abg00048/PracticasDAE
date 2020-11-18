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
public class CentroLogistico extends PuntoControl{
    
    /**
     * Provincia donde se encuentra el centro logístico
     */
    private String provincia;
    /**
     * Region donde se encuentra el centro logístico
     */
    private String region;
    /**
     * Oficinas conectadas con el centro logístico
     */
    private ArrayList<Oficina> oficinas;
    /**
     * Centros logísticos conectados con el centro logístico
     */
    private ArrayList<CentroLogistico> centros;
    
    public CentroLogistico(String provincia, String region){
        super();
        this.provincia = provincia;
        this.region = region;
        this.oficinas = new ArrayList<>();
        this.centros = new ArrayList<>();
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(ArrayList<Oficina> oficinas) {
        this.oficinas = oficinas;
    }

    public ArrayList<CentroLogistico> getCentros() {
        return centros;
    }

    public void setCentros(ArrayList<CentroLogistico> centros) {
        this.centros = centros;
    }
    
    
}
