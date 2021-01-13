/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.controladoresREST;

import com.proyectodae.controladoresREST.DTO.DTOCliente;
import com.proyectodae.servicios.ServicioLimpiadoBaseDatos;
import java.util.List;
import javax.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Test para controlador REST de clientes
 * @author alvar
 */
@SpringBootTest(classes = com.proyectodae.app.PaqueteriaApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControladorRESTTest {
    @Autowired
    ServicioLimpiadoBaseDatos limpiadorBaseDatos;

    @LocalServerPort
    int localPort;

    @Autowired
    MappingJackson2HttpMessageConverter springBootJacksonConverter;

    RestTemplateBuilder restTemplateBuilder;

    /**
     * Crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplateBuilder() {
        //restTemplateBuilder = new RestTemplateBuilder()
                //.rootUri("http://localhost:" + localPort + "/ujacoin")
                //.additionalMessageConverters(List.of(springBootJacksonConverter));
    }

    /**
     * Intento de creación de un cliente inválido
     */
    @Test
    public void testAltaClienteInvalido() {
        // Cliente con e-mail incorrecto!!!
        DTOCliente cliente = new DTOCliente(
                "11995667D",
                "Juan España España",
                "Cl La Luz, 13 - Jaén",
                "988674533",
                "jeegmail.com",
                "clave");

        TestRestTemplate restTemplate = new TestRestTemplate(restTemplateBuilder);
        ResponseEntity<DTOCliente> respuesta = restTemplate.postForEntity(
                "/clientes",
                cliente,
                DTOCliente.class
        );

        Assertions.assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    /**
     * test de alta y login de cliente
     */
    @Test
    public void testAltaYAccesoDatosCliente() {
        DTOCliente cliente = new DTOCliente(
                "11995667D",
                "Juan España España",
                "Cl La Luz, 13 - Jaén",
                "988674533",
                "jee@gmail.com",
                "clave");

        TestRestTemplate restTemplate = new TestRestTemplate(restTemplateBuilder);
        ResponseEntity<DTOCliente> respuestaAlta = restTemplate.postForEntity(
                "/clientes",
                cliente,
                DTOCliente.class
        );

        Assertions.assertThat(respuestaAlta.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        TestRestTemplate restTemplateAutenticado = new TestRestTemplate(restTemplateBuilder.basicAuthentication(cliente.getDni(), cliente.getClave()));

        ResponseEntity<DTOCliente> respuestaLogin = restTemplateAutenticado
                .getForEntity(
                        "/clientes/{dni}",
                        DTOCliente.class,
                        cliente.getDni()
                );

        Assertions.assertThat(respuestaLogin.getStatusCode()).isEqualTo(HttpStatus.OK);

        DTOCliente clienteLogin = respuestaLogin.getBody();
        Assertions.assertThat(clienteLogin.getDni()).isEqualTo(cliente.getDni());
    }

    /**
     * test de alta e intento de acceso a datos de otro cliente
     */
    @Test
    public void testAltaYAccesoDatosClienteDiferente() {
        DTOCliente cliente = new DTOCliente(
                "11995667D",
                "Juan España España",
                "Cl La Luz, 13 - Jaén",
                "988674533",
                "jee@gmail.com",
                "clave");

        TestRestTemplate restTemplate = new TestRestTemplate(restTemplateBuilder);
        restTemplate.postForEntity(
                "/clientes",
                cliente,
                DTOCliente.class
        );
        
        // Crear segundo cliente
        DTOCliente cliente2 = new DTOCliente(
                "99207668E",
                "Pedro Jaén, Jaén",
                "Cl La Paz, 20 - Jaén",
                "670701570",
                "pjj@gmail.com",
                "clavezzz"
        );

        restTemplate.postForEntity(
                "/clientes",
                cliente2,
                DTOCliente.class
        ).getBody();      

        TestRestTemplate restTemplateAutenticado = new TestRestTemplate(restTemplateBuilder.basicAuthentication(cliente.getDni(), cliente.getClave()));

        // Acceso con credenciales del primer cliente a la información del segundo
        ResponseEntity<DTOCliente> respuestaLogin = restTemplateAutenticado
                .getForEntity(
                        "/clientes/{dni}",
                        DTOCliente.class,
                        cliente2.getDni()
                );

        Assertions.assertThat(respuestaLogin.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @BeforeEach
    void limpiarBaseDatos() {
        limpiadorBaseDatos.limpiar();
    }
}
