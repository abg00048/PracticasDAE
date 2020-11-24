/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import java.util.List;

/**
 *
 * @author raulb
 */
public class CentroLogistico extends PuntoControl{
    
    /** Identificador del centro logistico */
    private String id;
    /** Localizacion del centro logistico */
    private String localizacion;
    /** Nombre del centro logistico */
    private String nombre;
    /** Lista de las provincias con oficinas asociadas a este centro logistico */
    private List<Oficina> provincias;
    /** Lista de centros logisticos asociados a este centro logistico */
    private List<CentroLogistico> conexiones;
    
    public CentroLogistico(){
    }
    
    public CentroLogistico(String id, String localizacion, String nombre, List<Oficina> provincias, List<CentroLogistico> conexiones){
        this.id = id;
        this.localizacion = localizacion;
        this.nombre = nombre;
        this.provincias = provincias;
        this.conexiones = conexiones;
    }
    
    @Override
    public void actualizarLocalizacion(){
        
    }
    
    @Override
    public void realizarEnvio(){
        
    }
    
}
