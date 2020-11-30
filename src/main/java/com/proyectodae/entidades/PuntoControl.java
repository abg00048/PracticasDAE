/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import com.proyectodae.servicio.Paqueteria;
import java.util.List;
/**
 *
 * @author raulb
 */
public abstract class PuntoControl {
    /** Actualiza la localizacion del envio cuando llega a un nuevo punto de control
     * @param localizacion nueva localizacion del envio
     * @param envio envio al que se le va a actualizar la localizacion */
    public void actualizarLocalizacion(String localizacion, Envio envio){
        envio.situacion = localizacion;
    }
   
    /** Realiza un envio siguiendo la ruta asociada al envi
     * @param servicio objeto de la clase Paqueteria que nos permite acceder a la lista con todos los puntos de control
     * @param envios lista donde se encuentra el envio que vamos a llevar a otro punto de control */
    public void realizarEnvio(Paqueteria servicio, List<Envio> envios){
        /** Cogemos el primer elemento de la lista porque */
        Envio envio = envios.get(0);
        String siguientePunto = envio.getRuta().get(0);
        /** Si la ruta solo tiene un elemento significa que el envio se encuentra en la localidad de destino y se puede proceder a su reparto */
        if (envio.getRuta().size() == 1){
            envio.setEstado("En reparto");
            envios.remove(0);
        }
        /** Si la ruta tiene mas de un elemento significa que hay que llevar el paquete a otro punto de control*/
        else{
            /** Busca todos los centros logisticos*/
            for (int i = 0; i < servicio.getCentrosLogisticos().size(); i++){
                /** Si el siguiente punto es un centro logistico hay que meter el envio en la lista de envios asociada al centro logistico*/
                if (servicio.getCentrosLogisticos().get(i).getLocalizacion() == siguientePunto){
                    servicio.getCentrosLogisticos().get(i).getEnvios().add(envio);
                    actualizarLocalizacion(servicio.getCentrosLogisticos().get(i).getLocalizacion(), envio);
                    envio.setEstado("Enviado");
                }
                else{
                    /** Busca todas las provincias asociadas al centro logistico "i"*/
                    for (int j = 0; j < servicio.getCentrosLogisticos().get(i).getProvincias().size(); j++){
                        /** Compara si la provincia "j" se corresponde con el siguiente punto al que debe enviarse el paquete.
                            Si coincide entonces se añade el envio a la lista de envios asociada al punto de control destino y se 
                            actualiza tanto el estado como la localizacion del envio*/
                        if (servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getProvincia() == siguientePunto){
                            servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getEnvios().add(envio);
                            actualizarLocalizacion(servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getProvincia(), envio);
                            envio.setEstado("Enviado");
                        }
                    }
                }
            }
            /** Por ultimo se elimina el envio de la lista de envios asociada al anterior punto de control y el punto de control de la lista ruta*/
            envio.getRuta().remove(0);
            envios.remove(0);
        }
    }

}
