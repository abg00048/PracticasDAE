/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectodae.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author raulb
 */
public class RandomString {
    
    public static final int ID_LENGTH = 5;

    public RandomString() {
    }    
    
    public String generateUniqueId() {
       return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
    }
    
}
