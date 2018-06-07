/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.lanchat.server;

import com.cyberprism.socketapi.ClientCallback;
import com.cyberprism.socketapi.ProxySocketServer;
import com.cyberprism.socketapi.ServerCallback;
import javafx.stage.Stage;

/**
 *
 * @author Chandramouliswaran
 */
public class Globals {

    public static ServerCallback serverCallback;
    public static ClientCallback clientCallback;
    public static ProxySocketServer proxySocketServer;
    public static Stage globalStage;
    
    public static String lastMessage = "";
    
}
