/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.servicio;

import com.proyectodae.entidades.Cliente;
import com.proyectodae.entidades.PuntoControl;
import com.proyectodae.excepciones.ClienteYaRegistrado;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 * @author jmait
 */
//@SpringBootTest(classes = com.proyectodae.app.PaqueteriaApp.class)
public class Tests {
/*    @Autowired
    Paqueteria paqueteria;
    
    @test void testAccesoServicioPaqueteriaApp(){
        Assertions.assertThat(PaqueteriaApp).isNotNull();
    }*/
}

/*@Test
public void testAltaClienteInvalido() {
    // Cliente con e-mail incorrecto!!!
    Cliente cliente = new Cliente(
            "JuanF Jimenez",
            "Avn. de España",
            "956558525",
            "jfj@ujaen.es",
            "passwrd");

    Assertions.assertThatThrownBy(() -> {
        Paqueteria.altaCliente(cliente); })
            .isInstanceOf(ConstraintViolationException.class);
}

@Test
public void testAltaYLoginClienteCuenta() {
    Cliente cliente = new Cliente(
            "JuanF Jimenez",
            "Avn. de España",
            "956558525",
            "jfj@ujaen.es",
            "passwrd");

    Paqueteria.altaCliente(cliente);
    Optional<Cliente> clienteLogin = Paqueria.loginCliente(cliente.getDni(), "passwrd");

    Assertions.assertThat(clienteLogin.isPresent()).isTrue();
    Assertions.assertThat(clienteLogin.get().getDni()).isEqualTo(cliente.getDni());
    Assertions.assertThat(clienteLogin.get().verCuentas()).isNotEmpty();
}

@Test
public void testAnadirEnvioCliente() {
    Cliente cliente = new Cliente(
            "JuanF Jimenez",
            "Avn. de España",
            "956558525",
            "jfj@ujaen.es",
            "passwrd");
            
    // Crear cliente
    Paqueteria.altaCliente(cliente);

    // Añadir un envio
    Envio envio = new Envio("1889182242865", "50", "Pendiente", LocalDate.of(2020, 12, 10));
    Cliente clienteLogin = Paqueteria.loginCliente(cliente.getDni(), "passwrd").get();
    Assertions.assertThat(clienteLogin.verEnvio(envio.getLocalizador())).isNotEmpty();
}*/   

