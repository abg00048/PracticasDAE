/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.controladoresREST.DTO;

import com.proyectodae.entidades.Cliente;
import com.proyectodae.entidades.Envio;
import com.proyectodae.entidades.Paquete;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO para recopilación de datos de envios
 * @author alvar
 */
public class DTOEnvio {
    /** Localizador */
    String localizador;
    /** Importe */
    Double importe;
    /** Estado */
    String estado;
    /** Fecha de Entrega */
    LocalDate fechaentrega;
     /** Cliente remitente*/
    private Cliente remitente;
    /** Cliente destinatario */
    private Cliente destinatario;
    /** Direccion de origen del envio */
    String origen;
    /** Direccion de destino del envio */
    String destino;
    /** Paquete asociado al envio */
    Paquete paquete;
    /** Situacion del paquete */
    String situacion;
    /** Ruta que tiene que seguir el envio */
    List<String> ruta;    

    public DTOEnvio(String localizador, Double importe, String estado, LocalDate fechaentrega, Cliente remitente, Cliente destinatario, String origen, String destino, Paquete paquete, String situacion, List<String> ruta) {
        this.localizador = localizador;
        this.importe = importe;
        this.estado = estado;
        this.fechaentrega = fechaentrega;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.origen = origen;
        this.destino = destino;
        this.paquete = paquete;
        this.situacion = situacion;
        this.ruta = ruta;
    }

    public DTOEnvio(Envio envio) {
        this.localizador = envio.getLocalizador();
        this.importe = envio.getImporte();
        this.estado = envio.getEstado();
        this.fechaentrega = envio.getFechaentrega();
        this.remitente = envio.getRemitente();
        this.destinatario = envio.getDestinatario();
        this.origen = envio.getOrigen();
        this.destino = envio.getDestino();
        this.paquete = envio.getPaquete();
        this.situacion = envio.getSituacion();
        this.ruta = envio.getRuta();
    }    
    
    public String getLocalizador() {
        return localizador;
    }

    public Double getImporte() {
        return importe;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaentrega() {
        return fechaentrega;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public String getSituacion() {
        return situacion;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public List<String> getRuta() {
        return ruta;
    }
    
}
