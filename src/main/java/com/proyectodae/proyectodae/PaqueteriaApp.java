/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.proyectodae;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import static com.proyectodae.util.JsonRead.dumpJSONElement;
import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author alvar
 */
@SpringBootApplication()
public class PaqueteriaApp {
    public static void main(String[] args) throws Exception, java.io.IOException {
        // Creación de servidor
        SpringApplication servidor = new SpringApplication(PaqueteriaApp.class);
        ApplicationContext context = servidor.run(args);
        
        //Lectura del fichero redujapack.json
        JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("redujapack.json");
        JsonElement datos = parser.parse(fr);
        dumpJSONElement(datos);
    }
}
