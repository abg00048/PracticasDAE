/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.servicio;

import com.proyectodae.entidades.CentroLogistico;
import com.proyectodae.entidades.Cliente;
import com.proyectodae.excepciones.ClienteYaRegistrado;
import com.proyectodae.repositorios.RepositorioClientes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Clase con los servicios de la empresa de paqueteria
 * @author alvar
 */
@Service
@Validated
public class Paqueteria {
    /** Mapa con la lista de clientes ordenada por DNI */
    Map<String, Cliente> clientes;
    /** Lista de los puntos de control */
    List<CentroLogistico> centrosLogisticos;
    
    @Autowired
    RepositorioClientes repositorioClientes;
    
    public Paqueteria() {
        clientes = new TreeMap<>();
        centrosLogisticos = new ArrayList();
    }

    public List<CentroLogistico> getCentrosLogisticos() {
        return centrosLogisticos;
    }
    
    public void setCentroLogistico(List<CentroLogistico> centrosLogisticos) {
        this.centrosLogisticos = centrosLogisticos;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
    }
    
    /**
     * Dar de alta cliente y crear una cuenta asociada
     * @param cliente el cliente a dar de alta
     */
    public void altaCliente(@NotNull @Valid Cliente cliente) {
        if (clientes.containsKey(cliente.getDni())) {
            throw new ClienteYaRegistrado();
        }
        
        //Registrar cliente
        clientes.put(cliente.getDni(), cliente);
        
        
    }
    
    /**
     * Realiza un login de un cliente
     * @param dni el DNI del cliente
     * @param clave la clave de acceso
     * @return el objeto de la clase Cliente asociado
     */
    public Optional<Cliente> loginCliente(@NotBlank String dni, @NotBlank String clave) {
        return Optional.ofNullable(clientes.get(dni)).filter((cliente)->cliente.claveValida(clave));
    }

    /** Busca en el envio con ese localizador asociado y devuelve la localizacion donde se encuentra el paquete
     * @param localizador identificador asociado al envio
     * @return  devuelve la localizacion en la que se encuentra el envio*/
    @Transactional
    public String localizarPaquete(String localizador){
        String localizacion = "";
        /** Busca en todos los centros logisticos */
        for (int i = 0; i < centrosLogisticos.size(); i++){
            /** Busca todas las provincias asociadas al centro logistico "i" */
            for (int j = 0; j < centrosLogisticos.get(i).getProvincias().size(); j++){
                /** Busca todos los envios asociados a la oficina de la provincia "j" */
                for (int k = 0; k < centrosLogisticos.get(i).getProvincias().get(j).getEnvios().size(); k++){
                    /** Devuelve la localizacion del paquete asociado al envio "k" */
                    if (centrosLogisticos.get(i).getProvincias().get(j).getEnvios().get(k).getLocalizador() == localizador){
                        localizacion = centrosLogisticos.get(i).getProvincias().get(j).getEnvios().get(k).getSituacion();
                    }
                }
            }
        }
        return localizacion;
    }
    
    /** Busco el envio asociado a ese localizador y devuelve el estado actual del envio
     * @param localizador identificador asociado al envio
     * @return  devuelve el estado del envio (En gestion, Enviado, En reparto)*/
    @Transactional
    public String estadoEnvio(String localizador){
        String estado = "";
        /** Busca en todos los centros logisticos */
        for (int i = 0; i < centrosLogisticos.size(); i++){
            /** Busca todas las provincias asociadas al centro logistico "i" */
            for (int j = 0; j < centrosLogisticos.get(i).getProvincias().size(); j++){
                /** Busca todos los envios asociados a la oficina de la provincia "j" */
                for (int k = 0; k < centrosLogisticos.get(i).getProvincias().get(j).getEnvios().size(); k++){
                    /** Devuelve el estado del envio "k" */
                    if (centrosLogisticos.get(i).getProvincias().get(j).getEnvios().get(k).getLocalizador() == localizador){
                        estado = centrosLogisticos.get(i).getProvincias().get(j).getEnvios().get(k).getEstado();
                    }
                }
            }
        }
        return estado;
    }
}
