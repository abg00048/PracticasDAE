/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.controladoresREST;

import com.proyectodae.controladoresREST.DTO.DTOCliente;
import com.proyectodae.entidades.Cliente;
import com.proyectodae.excepciones.ClienteNoRegistrado;
import com.proyectodae.excepciones.ClienteYaRegistrado;
import com.proyectodae.servicio.Paqueteria;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para los servicios de la paqueteria
 * @author alvar
 */
@RestController
@RequestMapping("/proyectodae")
public class ControladorREST {
    @Autowired
    Paqueteria servicios;
    
    /** Handler para excepciones de violación de restricciones */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handlerViolacionRestricciones(ConstraintViolationException e) {
        // return ResponseEntity.badRequest().body(e.getMessage());
    }

    /** Handler para excepciones de accesos de usuarios no registrados */
    @ExceptionHandler(ClienteNoRegistrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlerClienteNoRegistrado(ClienteNoRegistrado e) {
    }
    
    /** Creación de clientes */
    @PostMapping("/clientes")
    ResponseEntity<DTOCliente> altaCliente(@RequestBody DTOCliente cliente) {
        try {
            Cliente _cliente = servicios.altaCliente(cliente.aCliente());
            return ResponseEntity.status(HttpStatus.CREATED).body(new DTOCliente(_cliente));
        }
        catch(ClienteYaRegistrado e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    /** Login de clientes (temporal hasta incluir autenticación mediante Spring Security */
    @GetMapping("/clientes/{dni}")
    ResponseEntity<DTOCliente> verCliente(@PathVariable String dni) {
        Optional<Cliente> cliente = servicios.verCliente(dni);
        return cliente
                .map(c -> ResponseEntity.ok(new DTOCliente(c)))
                .orElse(ResponseEntity.notFound().build());
    }

}   

