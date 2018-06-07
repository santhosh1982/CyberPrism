/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.socketapi.structures;

import java.io.Serializable;

/**
 *
 * @author Chandramouliswaran
 */
public class ClientID implements Serializable {
    
    private Object id;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
    
    public boolean isLong() {
        return id instanceof Long;
    }
    
    public boolean isString() {
        return id instanceof String;
    }
    
    public boolean isObject() {
        return id instanceof Object;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
    
}
