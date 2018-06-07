/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.lanchat;

import com.cyberprism.lanchat.server.ChatServer;


/**
 *
 * @author Chandramouliswaran
 */
public class LANChatServer {

    public static void main(String[] args) {
        System.out.println("Starting Server...");
        
        ChatServer chatServer = new ChatServer();
        chatServer.start(9001);
    }
    
}
