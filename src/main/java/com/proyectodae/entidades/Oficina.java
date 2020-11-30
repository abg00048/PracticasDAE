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
import javax.validation.constraints.NotBlank;

/**
 *
 * @author raulb
 */
public class Oficina extends PuntoControl{
    
    /** Id del centro logistico al que pertenece la oficina */
    @NotBlank
    private String id;
    /** Provincia donde se encuentra la oficina*/
    @NotBlank
    private String provincia;
    /** Lista de envios que tiene que hacer la oficina */
    @NotBlank
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
    public void crearEnvio(Cliente remitente, Cliente destinatario, String origen, String destino){
        /** Localizador del envio */
        String localizador = Integer.toString((int)(Math.random()*1000000000));
        /** Importe total del envio */
        double importe = calcularPrecioEnvio();
        /** Estado actual del envio (En gestion, Enviado, En reparto) */
        String estado = "En gestion";
        /** Fecha de entrega del paquete */
        LocalDate fecha = LocalDate.now().plusDays((int)(Math.random()*6));
        /** Localizacion actual del paquete */
        String localizacion = origen;
        /** Ruta que tiene que hacer el envio */
        List<String> ruta = new ArrayList();
        /** Paquete asociado al envio */
        Paquete paquete = new Paquete(localizador, Double.toString(medirPaquete()), Double.toString(pesarPaquete()));
        /** Creamos el envio */
        Envio envio = new Envio(localizador, importe, estado, fecha, remitente, destinatario, origen, destino,paquete, localizacion, ruta);
        
        /** Añadimos el nuevo envio a la lista de envios de la oficina*/
        envios.add(envio);

    }
    
    /** Crea una ruta para hacer un envio */
    public List<String> crearRuta(Paqueteria servicio, Envio envio){
        List<String> ruta = new ArrayList();
        /** Se añade la provincia a la ruta si el lugar de origen y el lugar de destino son el mismo */
        if (envio.destino == envio.origen){
            ruta.add(envio.destino);
        }
        else{
            /** Id del centro logistico de la provincia desde la que se envia el paquete */
            String idCentroOrigen = "";
            /** Id del centro logistico asociado a la provincia de destino del paquete */
            String idCentroDestino = "";
            for (int i = 0; i < servicio.getCentrosLogisticos().size(); i++){
                /** Compara si la provincia del centro logistico y la provincia de la que sale el paquete son la misma */
                if (servicio.getCentrosLogisticos().get(i).getLocalizacion() == envio.origen){
                    idCentroOrigen = servicio.getCentrosLogisticos().get(i).getId();
                }
                for (int j = 0; j < servicio.getCentrosLogisticos().get(i).getProvincias().size(); j++){
                    /** Compara si alguna provincia del centro logistico "i" es igual a la provincia destino del paquete */
                    if (servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getProvincia() == envio.destino){
                        idCentroDestino = servicio.getCentrosLogisticos().get(i).getId();
                    }
                }
            }
            /** Compara si el envio se hace entre las provincias asociadas a un mismo centro logistico */
            if (idCentroOrigen == idCentroDestino){
                ruta.add(envio.origen);
                ruta.add(envio.destino);
            }
            else{
                // Por hacer
            }
        }
        return ruta;
    }
    
    
}
