/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.servicio;


import com.proyectodae.entidades.Cliente;
import com.proyectodae.entidades.Paquete;
import com.proyectodae.entidades.Envio;
import com.proyectodae.servicio.Paqueteria;

//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.annotation.DirtiesContext.MethodMode;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author jmait
 */

@SpringBootTest(classes = com.proyectodae.app.PaqueteriaApp.class)
public class ServicePaqueteriaTests {
    @Autowired
    Paqueteria paqueteria;
    
    @Test
    void testAccesoServicioPaqueteria(){
        Assertions.assertThat(paqueteria).isNotNull();
    }
    
    @Test
    public void testAltaClienteInvalido(){
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);
        Assertions.assertThatThrownBy(() -> {
            paqueteria.altaCliente(cliente); })
                .isInstanceOf(ConstraintViolationException.class);
    }
    
    @Test
   // @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void testAltaYLoginClienteCuenta() {
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);

        paqueteria.altaCliente(cliente);
        Optional<Cliente> clienteLogin = paqueteria.loginCliente(cliente.getDni(), clave);

        Assertions.assertThat(clienteLogin.isPresent()).isTrue();
        Assertions.assertThat(clienteLogin.get().getDni()).isEqualTo(cliente.getDni());
        Assertions.assertThat(clienteLogin.get().getDireccion()).isNotEmpty();
    }

    @Test
    public void testAnadirEnvioCliente() {
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);

        String clave2 = "psswd";
        
        Cliente cliente2 = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave2);

        // Crear cliente
        paqueteria.altaCliente(cliente);
        paqueteria.altaCliente(cliente2);
        
        // Crear Ruta
        List<String> ruta = new ArrayList<String>();
        
        // Paquete
        Paquete paquete = new Paquete(
            "887827486",
            "25x6x12",
            "26.2"
        );
        
        // Añadir un envio
        Envio envio = new Envio("1889182242865", 50.0, "Pendiente", LocalDate.of(2020, 12, 10), cliente, cliente2, "jaen", "granada", paquete, "jaen", ruta);
        Cliente clienteLogin = paqueteria.loginCliente(cliente.getDni(), clave).get();
    } 
    
}

