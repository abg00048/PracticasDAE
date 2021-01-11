/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proyectodae.entidades.puntosControl.CentroLogistico;
import com.proyectodae.servicio.Paqueteria;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author alvar
 */
@SpringBootApplication(scanBasePackages={
    "com.proyectodae.servicio",
    "com.proyectodae.repositorios",
    "com.proyectodae.controladoresREST",
    "com.proyectodae.seguridad"
})
@EntityScan(basePackages="com.proyectodae.entidades")
public class PaqueteriaApp {
    public static void main(String[] args) throws Exception{
        // Creación de servidor
        SpringApplication.run(PaqueteriaApp.class, args);
        
        //Lectura del fichero redujapack.json
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("redujapack.json"));

            // convert JSON array to list
            List<CentroLogistico> centrosLogisticos = new Gson().fromJson(reader, new TypeToken<List<CentroLogistico>>() {}.getType());
            
            // establece los puntos de control del servicio de paqueteria
            Paqueteria servicio = new Paqueteria();
            servicio.setCentroLogistico(centrosLogisticos);

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
