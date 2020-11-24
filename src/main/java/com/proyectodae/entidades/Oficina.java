/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import com.proyectodae.servicio.Paqueteria;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulb
 */
public class Oficina extends PuntoControl{
    
    /** Id del centro logistico al que pertenece la oficina */
    private String id;
    /** Provincia donde se encuentra la oficina*/
    private String provincia;
    /** Lista de envios que tiene que hacer la oficina */
    private List<Envio> envios;
    /** Centro logistico asociado a la oficina */
    //CentroLogistico centro;
    
    public Oficina(){
    }
    
    public Oficina(String id, String provincia){
        this.id = id;
        this.provincia = provincia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Envio> getEnvios() {
        return envios;
    }
    
    /** Dimensiones del paquete en cm
     * @return  medidas del paquete (suponemos que todos los lados miden igual)**/
    public double medirPaquete(){
        return Math.random()*80.0+10.0;
    }
    
    /** Peso del paquete en g
     * @return  peso del paquete (suponemos que puede pesar mas de 20kg)*/
    public double pesarPaquete(){
        return Math.random()*20000.0+10000.0;
    }
    
    public double calcularPrecioEnvio(){
        /** Precio inicial del envio (en euros) */
        int tarifaBase = 5; 
        /** Precio añadido por cada kg que pese el paquete */
        double tarifaVariable = 0.5;
        
        return (pesarPaquete()*tarifaVariable) + tarifaBase;
    }
    
    /** Crea un envio con los datos del paquete
     * @param remitente cliente que envia el paquete
     * @param destinatario cliente que recibe el paquete*/
    public void crearEnvio(Cliente remitente, Cliente destinatario){
        Paqueteria paqueteria = new Paqueteria();
        /** Localizador del envio */
        String localizador = Integer.toString((int)(Math.random()*1000000000));
        /** Importe total del envio */
        double importe = calcularPrecioEnvio();
        /** Estado actual del envio (En gestion, Enviado, En reparto) */
        String estado = "En gestion";
        /** Fecha de entrega del paquete */
        LocalDate fecha = LocalDate.now().plusDays((int)(Math.random()*6));
        /** Localizacion actual del paquete */
        String localizacion = this.provincia;
        /** Ruta que tiene que hacer el envio */
        String[] ruta = null;
        /** Paquete asociado al envio */
        Paquete paquete = new Paquete(localizador, Double.toString(medirPaquete()), Double.toString(pesarPaquete()));
        /** Creamos el envio */
        Envio envio = new Envio(localizador, importe, estado, fecha, remitente, destinatario, paquete, localizacion, ruta);
        
        /** Añadimos el nuevo envio a la lista de envios de la oficina*/
        envios.add(envio);

    }
    
    /** Crea una ruta para hacer un envio */
    public void crearRuta(){
        
    }
    
    @Override
    public void actualizarLocalizacion(){
    }
    
    @Override
    public void realizarEnvio(){
        
    }
}
