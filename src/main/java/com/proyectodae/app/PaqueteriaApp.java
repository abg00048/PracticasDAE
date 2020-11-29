/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proyectodae.entidades.CentroLogistico;
import com.proyectodae.servicio.Paqueteria;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author alvar
 */
@SpringBootApplication
public class PaqueteriaApp {
    public static void main(String[] args) throws Exception, java.io.IOException {
        // Creación de servidor
        SpringApplication servidor = new SpringApplication(PaqueteriaApp.class);
        ApplicationContext context = servidor.run(args);
        
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
