/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.entidades;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;


/**
 *
 * @author jmait
 */
public class ClienteTest {
    
    public ClienteTest(){}
    
    @Test
    void testValidacionCliente() {
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);
        
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
                
        Assertions.assertThat(violations).isEmpty();
    }
    
    @Test
    void testComprobacionClave() {
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);
        
        Assertions.assertThat(cliente.claveValida(clave)).isTrue();
    }
}


/*import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;


/**
 *
 * @author jmait
 */
/*@SpringBootTest(classes = com.proyectodae.app.PaqueteriaApp.class)
public class ServicioPaqueteriaTest {
    
    
    @Autowired
    Paqueteria paqueteria;
    
    @Test 
    void testAccesoServicioPaqueteriaApp(){
        Assertions.assertThat(PaqueteriaApp).isNotNull();
    }


    @Test
    public void testAltaClienteInvalido() {
        // Cliente con e-mail incorrecto!!!
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);

        Assertions.assertThatThrownBy(() -> {
            Paqueteria.altaCliente(cliente); })
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void testAltaYLoginClienteCuenta() {
        String clave = "psswd";
        
        Cliente cliente = new Cliente(
                "191268951N",
                "JuanF Jimenez",
                "Avn. de España",
                "956558525",
                "jfj@ujaen.es",
                clave);

        Paqueteria.altaCliente(cliente);
        Optional<Cliente> clienteLogin = Paqueria.loginCliente(cliente.getDni(), "passwrd");

        Assertions.assertThat(clienteLogin.isPresent()).isTrue();
        Assertions.assertThat(clienteLogin.get().getDni()).isEqualTo(cliente.getDni());
        Assertions.assertThat(clienteLogin.get().verCuentas()).isNotEmpty();
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

        // Crear cliente
        Paqueteria.altaCliente(cliente);

        // Añadir un envio
        Envio envio = new Envio("1889182242865", "50", "Pendiente", LocalDate.of(2020, 12, 10));
        Cliente clienteLogin = Paqueteria.loginCliente(cliente.getDni(), "passwrd").get();
        Assertions.assertThat(clienteLogin.verEnvio(envio.getLocalizador())).isNotEmpty();
    } 


}*/
