/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.servicios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author alvar
 */

@Service
public class ServicioLimpiadoBaseDatos {
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    TransactionTemplate transactionTemplate;

    /** 
     * Lista de entidades a borrar. Ojo: el orden es muy importante
     * para evitar errores de violación de integridad 
     */
    final String[] entidades = {
        "PuntoControl", 
        "Cliente",
        "Envio"
    };
    
    final String deleteFrom = "delete from ";
    
    /** Realizar borrado */
    public void limpiar() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            for (String tabla : entidades) {
                em.createQuery(deleteFrom + tabla).executeUpdate();
            }
        });
    }
}
