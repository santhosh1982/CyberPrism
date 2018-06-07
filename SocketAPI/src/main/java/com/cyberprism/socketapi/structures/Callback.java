/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.socketapi.structures;

/**
 *
 * @author Chandramouliswaran
 */
@FunctionalInterface
public interface Callback {
    Object[] apply(Object... params);
}
