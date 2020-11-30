/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.repositorios;

import com.proyectodae.entidades.Cliente;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alvaro
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RepositorioClientes {
    @PersistenceContext
    EntityManager em;
    
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Cliente> buscar(String dni) {
        return Optional.ofNullable(em.find(Cliente.class, dni));
    }
    
    public void guardar(Cliente cliente) {
        em.persist(cliente);
    }
    
    public void actualizar(Cliente cliente) {
        em.merge(cliente);
    }
}

