/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.socketapi;

import com.cyberprism.socketapi.structures.Argument;
import com.cyberprism.socketapi.structures.Callback;
import com.cyberprism.socketapi.structures.Command;
import java.util.List;

/**
 *
 * @author Chandramouliswaran
 */
public interface ClientCallback {
    Callback received(Command command,List<Argument> arguments); 
}
