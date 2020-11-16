/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.proyectodae;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
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
    Float importe;
    /** Estado */
    @NotBlank
    String estado;
    /** Fecha de Entrega */
    @NotBlank
    String fechaentrega;
     /** Cliente */
    @NotNull
    private Cliente cliente;

    
     /** Lista de registros */
    List<RegistroEnvio> registros;

    

    public Envio(String localizador, Float importe, String estado, String fechaentrega, Cliente cliente) {
        
        this.localizador = localizador;
        this.importe = importe;
        this.estado = estado;
        this.fechaentrega = fechaentrega;
        registros = new ArrayList<>();
        
    }

    public String getlocalizador() {
        return localizador;
    }

    public Float getimporte() {
        return importe;
    }

    public String getestado() {
        return estado;
    }

    public String getfechaentrega() {
        return fechaentrega;
    }
    
    public void asignarPaquete(Paquete p){
        String id = p.id;
        String dimensiones = p.dimensiones;
        String peso = p.peso;
        Float precio = p.precio;
        
    }
    
    public void nuevoRegistro(RegistroEnvio registro) {
        
        registro.setFechaEntrada(LocalDate.MIN);
        registro.setFechaSalida(LocalDate.MAX);
        
        registros.add(registro);
    }

    
    
    public List<RegistroEnvio> listaRegistros(LocalDate fechaEntrada, LocalDate fechaSalida) {
        LocalDate fechaEntradaConsulta = Optional.ofNullable(fechaSalida).orElseGet(
                () -> {
                    LocalDate ahora = LocalDate.now();
                    return ahora.minusDays(ahora.getDayOfMonth() - 1);
                }
        );
        LocalDate fechaSalidaConsulta = Optional.ofNullable(fechaSalida).orElse(LocalDate.now());

        return registros.stream().filter(m ->
                m.getFechaEntrada().isAfter(fechaEntradaConsulta) &&
                m.getFechaSalida().isBefore(fechaSalidaConsulta)
        ).collect(Collectors.toList());
    }

    public List<RegistroEnvio> listarRegistros() {
        return listaRegistros(null, null);
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    
}
