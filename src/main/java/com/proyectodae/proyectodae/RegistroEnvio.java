/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.proyectodae;


import java.time.LocalDate;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author jmait
 */
public class RegistroEnvio {

    

    /** Fecha de entrada */
    @PastOrPresent
    private LocalDate fechaEntrada;
    
    /** Fecha de salida */
    @PastOrPresent
    private LocalDate fechaSalida;

    /**
     * Constructor del registro
     */
    public RegistroEnvio() {
        this.fechaEntrada = LocalDate.now();
        this.fechaSalida = LocalDate.now();
    }

    /**
     * @return the fechaEntrada
     */
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * @param fechaEntrada the fechaEntrada to set
     */
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * @return the fechaSalida
     */
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
