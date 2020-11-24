/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;


import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;


/**
 *
 * @author jmait
 */
public class Envio {
    /** Localizador */
    @NotBlank
    String localizador;
    /** Importe */
    @Positive
    Double importe;
    /** Estado */
    @NotBlank
    String estado;
    /** Fecha de Entrega */
    @NotBlank
    LocalDate fechaentrega;
     /** Cliente remitente*/
    @NotNull
    private Cliente remitente;
    /** Cliente destinatario */
    private Cliente destinatario;
    /** Paquete asociado al envio */
    Paquete paquete;
    /** Situacion del paquete */
    String situacion;
    /** Ruta que tiene que seguir el envio */
    String[] ruta;


    public Envio(String localizador, Double importe, String estado, LocalDate fechaentrega, Cliente remitente, Cliente destinatario, Paquete paquete, String situacion, String[] ruta) {
        
        this.localizador = localizador;
        this.importe = importe;
        this.estado = estado;
        this.fechaentrega = fechaentrega;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.paquete = paquete;
        this.situacion = situacion;
        this.ruta = ruta;
        
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(LocalDate fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String[] getRuta() {
        return ruta;
    }

    public void setRuta(String[] ruta) {
        this.ruta = ruta;
    }
    
}
