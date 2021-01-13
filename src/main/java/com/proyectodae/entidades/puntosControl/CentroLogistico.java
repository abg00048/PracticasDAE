/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades.puntosControl;

import com.proyectodae.entidades.Envio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author raulb
 */
@Entity
public class CentroLogistico extends PuntoControl implements Serializable{
    
    /** Identificador del centro logistico */
    @Id
    @Size(min=1, max=2)
    @NotBlank
    private String id;
    /** Nombre del centro logistico */
    @NotBlank
    private String nombre;
    /** Localizacion del centro logistico */
    @NotBlank
    private String localizacion;
    /** Lista de las provincias con oficinas asociadas a este centro logistico */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="centroLogistico")
    @NotBlank
    private List<Oficina> provincias;
    /** Lista de centros logisticos asociados a este centro logistico */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="centroLogistico")
    @NotBlank
    private List<CentroLogistico> conexiones;
    
    @OneToMany
    private List<Envio> envios;
    
    public CentroLogistico(){
    }
    
    public CentroLogistico(String id, String nombre, String localizacion, List<Oficina> provincias, List<CentroLogistico> conexiones){
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.provincias = provincias;
        this.conexiones = conexiones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Oficina> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Oficina> provincias) {
        this.provincias = provincias;
    }

    public List<CentroLogistico> getConexiones() {
        return conexiones;
    }

    public void setConexiones(List<CentroLogistico> conexiones) {
        this.conexiones = conexiones;
    }

    public List<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(List<Envio> envios) {
        this.envios = envios;
    }
    
}
