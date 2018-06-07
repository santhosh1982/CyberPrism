/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.lanchat.server;

import com.cyberprism.socketapi.ClientCallback;
import com.cyberprism.socketapi.ProxySocketServer;
import com.cyberprism.socketapi.ServerCallback;
import com.cyberprism.socketapi.structures.Argument;
import com.cyberprism.socketapi.structures.Callback;
import com.cyberprism.socketapi.structures.Command;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chandramouliswaran
 */
public class ChatServer {
    
    final static List<Command> commands = new ArrayList<Command>();
    
    static {
        commands.add(new Command("set:connect", "[connect]"));
        commands.add(new Command("disconnect", "[disconnect]"));
        commands.add(new Command("login", "[login]"));
        commands.add(new Command("sendmessage","[sendmessage]"));
        commands.add(new Command("list:chats","[listchats]"));
        commands.add(new Command("list:users","[listusers]"));
        commands.add(new Command("set:logout","[logout]"));
    }
    
    
    public void start(int port) {
        
        ProxySocketServer proxySocketServer = new ProxySocketServer(ProxySocketServer.SERVER_CALLBACK_ONLY, "lanchat-"+System.currentTimeMillis());
        
        ServerCallback serverCallback = new ServerCallback() {
            @Override
            public boolean serverConnected() {
                return true;
            }

            @Override
            public boolean serverDisconnected() {
                return true;
            }

            @Override
            public boolean shouldReconnect() {
                return true;
            }

            @Override
            public boolean clientConnected(Socket lastSocket) {
                System.out.println("Client Connected --- "+lastSocket.getInetAddress().getHostName()+"/"+lastSocket.getInetAddress().getHostAddress()+"@"+lastSocket.getPort());
                return true;
            }

            @Override
            public boolean serverStartError() {
                System.out.println("Server Start Error!");
                return false;
            }
        };
        
        ClientCallback clientCallback = new ClientCallback() {
            @Override
            public Callback received(Command command, List<Argument> arguments) {
                return (params)->{
                    return null;
                };
            }
        };
        
        Globals.serverCallback = proxySocketServer.addServerCallback(serverCallback);
        Globals.clientCallback = proxySocketServer.addClientCallback(clientCallback);
        
        Globals.proxySocketServer = proxySocketServer;
        Globals.proxySocketServer.start("localhost", port);
        System.out.println("Server Started!");
        
    }
}
