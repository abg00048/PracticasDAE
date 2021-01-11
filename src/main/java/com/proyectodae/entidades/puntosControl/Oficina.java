/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades.puntosControl;

import com.proyectodae.entidades.Cliente;
import com.proyectodae.entidades.Envio;
import com.proyectodae.entidades.Paquete;
import com.proyectodae.entidades.puntosControl.PuntoControl;
import com.proyectodae.servicio.Paqueteria;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author raulb
 */
@Entity
public class Oficina extends PuntoControl{
    
    /** Id del centro logistico al que pertenece la oficina */
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="centroLogistico_id")
    @Size(min=1, max=2)
    @NotBlank
    private String id;
    /** Provincia donde se encuentra la oficina*/
    @NotBlank
    private String provincia;
    /** Lista de envios que tiene que hacer la oficina */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="oficina")
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
     * @param destinatario cliente que recibe el paquete
     * @param origen lugar del que procede el paquete
     * @param destino lugar al que va el paquete*/
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
    
    /** Crea una ruta para hacer un envio
     * @param servicio servicio de paqueteria
     * @param envio envio asociado a la oficina al que se le va a asignar una ruta
     * @return  devuelve la ruta asociada al envio*/
    public List<String> crearRuta(Paqueteria servicio, Envio envio){
        List<String> ruta = new ArrayList();
        /** Se añade la provincia a la ruta si el lugar de origen y el lugar de destino son el mismo */
        if (envio.getDestino() == null ? envio.getOrigen() == null : envio.getDestino().equals(envio.getOrigen())){
            ruta.add(envio.getDestino());
        }
        else{
            /** Id del centro logistico de la provincia desde la que se envia el paquete */
            String idCentroOrigen = "";
            /** Guarda la posicion dentro de la lista del centro logistico de origen */
            int centroOrigen = 0;
            /** Id del centro logistico asociado a la provincia de destino del paquete */
            String idCentroDestino = "";
            /** Guarda la posicion dentro de la lista del centro logistico de destino */
            int centroDestino = 0;
            for (int i = 0; i < servicio.getCentrosLogisticos().size(); i++){
                /** Compara si el envio se hace desde un centro logistico */
                if (servicio.getCentrosLogisticos().get(i).getLocalizacion() == null ? envio.getOrigen() == null : servicio.getCentrosLogisticos().get(i).getLocalizacion().equals(envio.getOrigen())){
                    idCentroOrigen = servicio.getCentrosLogisticos().get(i).getId();
                    centroOrigen = i;
                    ruta.add(envio.getOrigen());
                }
                for (int j = 0; j < servicio.getCentrosLogisticos().get(i).getProvincias().size(); j++){
                    /** Compara si alguna provincia del centro logistico "i" es igual a la provincia origen del paquete */
                    if (servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getProvincia() == null ? envio.getOrigen() == null : servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getProvincia().equals(envio.getOrigen())){
                        idCentroOrigen = servicio.getCentrosLogisticos().get(i).getId();
                        centroOrigen = i;
                        ruta.add(servicio.getCentrosLogisticos().get(i).getLocalizacion());
                        ruta.add(envio.getOrigen());
                    }
                    /** Compara si alguna provincia del centro logistico "i" es igual a la provincia destino del paquete */
                    if (servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getProvincia() == null ? envio.getDestino() == null : servicio.getCentrosLogisticos().get(i).getProvincias().get(j).getProvincia().equals(envio.getDestino())){
                        idCentroDestino = servicio.getCentrosLogisticos().get(i).getId();
                        centroDestino = i;
                    }
                }
            }
            /** Compara si el envio se hace entre las provincias asociadas a un mismo centro logistico */
            if (idCentroOrigen == null ? idCentroDestino == null : idCentroOrigen.equals(idCentroDestino)){
                ruta.add(envio.getOrigen());
                ruta.add(envio.getDestino());
            }
            /** El envio se hace entre dos provincias asociadas a dos centros logisticos diferentes */
            else{
                Boolean encontrado = false;
                for (int i = 0; i < servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().size(); i++){
                    if (servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getId() == null ? idCentroDestino == null : servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getId().equals(idCentroDestino)){
                        ruta.add(servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getLocalizacion());
                        encontrado = true;
                        if (servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getLocalizacion() == null ? envio.getDestino() != null : !servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getLocalizacion().equals(envio.getDestino())){
                            for (int j = 0; j < servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getProvincias().size(); j++){
                                if (servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getProvincias().get(j).getProvincia() == null ? envio.getDestino() == null : servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getProvincias().get(j).getProvincia().equals(envio.getDestino())){
                                    ruta.add(envio.getDestino());
                                }
                            }
                        }
                    }
                }
                if (encontrado = false){
                    while(encontrado == false){
                        idCentroOrigen = servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(0).getId();
                        for (int i = 0; i < servicio.getCentrosLogisticos().size(); i++){
                            if (servicio.getCentrosLogisticos().get(i).getId() == null ? idCentroOrigen == null : servicio.getCentrosLogisticos().get(i).getId().equals(idCentroOrigen)){
                                centroOrigen = i;
                            }
                        }
                        for (int i = 0; i < servicio.getCentrosLogisticos().get(centroOrigen).getProvincias().size(); i++){
                            if (servicio.getCentrosLogisticos().get(centroOrigen).getProvincias().get(i).getProvincia() == null ? envio.getDestino() == null : servicio.getCentrosLogisticos().get(centroOrigen).getProvincias().get(i).getProvincia().equals(envio.getDestino())){
                                ruta.add(envio.getDestino());
                                encontrado = true;
                            }
                        }
                        if (encontrado == false){
                            for (int i = 0; i < servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().size(); i++){
                                if (servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getId() == null ? idCentroDestino == null : servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getId().equals(idCentroDestino)){
                                    ruta.add(servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getLocalizacion());
                                    encontrado = true;
                                    if (servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getLocalizacion() == null ? envio.getDestino() != null : !servicio.getCentrosLogisticos().get(centroOrigen).getConexiones().get(i).getLocalizacion().equals(envio.getDestino())){
                                        ruta.add(envio.getDestino());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ruta;
    }
    
    
}
