/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.socketapi;

import java.net.Socket;
import java.util.stream.Stream;

/**
 *
 * @author Chandramouliswaran
 */
public interface ServerCallback {
    boolean serverConnected();
    boolean serverDisconnected();
    boolean shouldReconnect();
    boolean clientConnected(Socket lastSocket);
    boolean serverStartError();
}
