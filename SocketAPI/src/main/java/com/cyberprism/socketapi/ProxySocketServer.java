/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.socketapi;

import com.cyberprism.socketapi.structures.Argument;
import com.cyberprism.socketapi.structures.Callback;
import com.cyberprism.socketapi.structures.ClientID;
import com.cyberprism.socketapi.structures.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Chandramouliswaran
 */
public class ProxySocketServer {
    
    private AtomicReference<List<ServerCallback>> serverCallback = new AtomicReference<List<ServerCallback>>();
    private AtomicReference<List<ClientCallback>> clientCallback = new AtomicReference<List<ClientCallback>>();
    private AtomicReference<List<Command>> clientCommands = new AtomicReference<List<Command>>();
    
    private AtomicReference<List<Socket>> clients = new AtomicReference<List<Socket>>();
    private AtomicReference<Map<ClientID,Socket>> clientIDs = new AtomicReference<Map<ClientID,Socket>>();
    
    public static final int SERVER_QUEUED=0;
    public static final int SERVER_CALLBACK_ONLY=1;
    
    private int serverType;
    private String serverName;

    public ProxySocketServer(int serverType, String serverName) {
        this.serverType = serverType;
        this.serverName = serverName;
        this.serverCallback.set(new ArrayList<ServerCallback>());
        this.clientCallback.set(new ArrayList<ClientCallback>());
        this.clientCommands.set(new ArrayList<Command>());
        this.clients.set(new ArrayList<Socket>());
        this.clientIDs.set(new HashMap<ClientID,Socket>());
    }
    
    public synchronized ServerCallback addServerCallback(ServerCallback callback) {
        synchronized(serverCallback) {
           serverCallback.get().add(callback);
           
           return callback;
        }
    }
    
    public synchronized ClientCallback addClientCallback(ClientCallback callback) {
        synchronized(clientCallback) {
           clientCallback.get().add(callback);
           
           return callback;
        }
    }
    
    public synchronized Command addCommand(Command command) {
        synchronized(this.clientCommands) {
            final List<Command> commands = this.clientCommands.get();
            commands.add(command);
            
            return command;
        }        
    }
    

    public AtomicReference<List<ServerCallback>> getServerCallback() {
        return serverCallback;
    }

    public void setServerCallback(AtomicReference<List<ServerCallback>> serverCallback) {
        this.serverCallback = serverCallback;
    }

    public int getServerType() {
        return serverType;
    }

    public void setServerType(int serverType) {
        this.serverType = serverType;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
    private boolean stop=false;
    private boolean accepted=false;
    
    private Socket lastSocket = null;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    
    public void start(String host,int port) {
        final Thread serverThread =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket server1=null;
                    
                    System.out.println("Starting Server on "+host+"@"+port);
                    try {
                        server1 = new ServerSocket(port);
                    } catch(BindException ex) {
                        if(!updateServerStartError()) {
                            return;
                        }
                    }
                    final ServerSocket server = server1;
                    
                    System.out.println("Started Server on "+host+"@"+port);
                    
                    if(updateServerConnected()) {
                        System.out.println(System.currentTimeMillis()+": " + getServerName()+" Server Connected...");
                        
                    } else {
                        if(!updateShouldReconnect()) {
                            System.out.println(System.currentTimeMillis()+": " + getServerName()+" Server Not Reconnecting... Exiting!");
                            return;
                        }
                    }
                    
                    while(!stop) {
                        final Thread acceptThread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    accepted = false;
                                    lastSocket = server.accept();
                                    accepted = true;
                                } catch (IOException ex) {
                                    Logger.getLogger(ProxySocketServer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        
                        acceptThread.start();
                        
                        while(!stop && !accepted && lastSocket==null) 
                            ;
                        
                        if(stop) {
                            acceptThread.stop();
                            if(updateServerDisconnected()) {
                                return;
                            }
                        }
                        
                        if(lastSocket!=null) {
                            if(updateClientConnected(lastSocket)) {
                                final Socket client = lastSocket;
                                
                                final Thread clientThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            final BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                            final PrintWriter pw = new PrintWriter(client.getOutputStream());
                                            
                                            String line="";
                                            
                                            while((line=reader.readLine())!=null) {
                                                final String command = line.split(":")[0];
                                                final String arguments = line.split(":")[1];
                                                
                                                List<Command> commands2 = null;
                                                synchronized(commands2 = clientCommands.get()) {
                                                    if(commands2.size()>0) {
                                                        Command command2 = commands2.stream().filter(c->command.equals(c.getCommand())).findFirst().orElse(new Command("",""));
                                                        
                                                        if(!command2.getName().equals("")) {
                                                            final List<Argument> arguments2 = Arrays.stream(arguments.split(",")).map(a->new Argument(a, a)).collect(Collectors.toList());
                                                            final List<String> values = new ArrayList<String>();
                                                            
                                                            if(command2.getName().startsWith("list:")) {
                                                                while(!(line=reader.readLine()).equals("---end of list---")) {
                                                                    values.add(line);
                                                                }
                                                            } else if(command2.getName().startsWith("setid:")) {
                                                                ClientID clientId = new ClientID();
                                                                clientId.setId(command2.getId());
                                                                
                                                                synchronized(clientIDs) {
                                                                    clientIDs.get().put(clientId, client);
                                                                }
                                                            }
                                                            
                                                            List<ClientCallback> clientCallbacks=null;
                                                            
                                                            synchronized(clientCallbacks = clientCallback.get()) {
                                                                clientCallbacks.stream().forEach(cc->{
                                                                   Callback callback = cc.received(command2, arguments2);
                                                                   
                                                                   if(values.size()==0) {
                                                                        Object[] returned = callback.apply(command2,arguments);
                                                                        reply(returned);
                                                                   } else {
                                                                        Object[] returned = callback.apply(command2,arguments,values);
                                                                        reply(returned);
                                                                   }
                                                                });
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            
                                        } catch (IOException ex) {
                                            Logger.getLogger(ProxySocketServer.class.getName()).log(Level.SEVERE, null, ex);
                                        } finally {
                                        }
                                    }
                                });
                                
                                clientThread.start();
                            }
                        }
                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ProxySocketServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


        });
        
        serverThread.start();
    }
    
    public synchronized boolean updateServerStartError() {
        synchronized(this.serverCallback) {
            final List<ServerCallback> callbacks = this.serverCallback.get();
            
            if(callbacks.stream().map(c->c.serverStartError()).filter(b->b.booleanValue()==true).count()==callbacks.size()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public synchronized boolean updateServerConnected() {
        synchronized(this.serverCallback) {
            final List<ServerCallback> callbacks = this.serverCallback.get();
            
            if(callbacks.stream().map(c->c.serverConnected()).filter(b->b.booleanValue()==true).count()==callbacks.size()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public synchronized boolean updateServerDisconnected() {
        synchronized(this.serverCallback) {
            final List<ServerCallback> callbacks = this.serverCallback.get();
            
            if(callbacks.stream().map(c->c.serverDisconnected()).filter(b->b.booleanValue()==true).count()==callbacks.size()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public synchronized boolean updateShouldReconnect() {
        synchronized(this.serverCallback) {
            final List<ServerCallback> callbacks = this.serverCallback.get();
            
            if(callbacks.stream().map(c->c.shouldReconnect()).filter(b->b.booleanValue()==true).count()==callbacks.size()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean updateClientConnected(Socket lastSocket) {
        synchronized(this.serverCallback) {
            final List<ServerCallback> callbacks = this.serverCallback.get();

            if(callbacks.stream().map(c->c.clientConnected(lastSocket)).filter(b->b.booleanValue()==true).count()==callbacks.size()) {
                return true;
            } else {
                return false;
            }
        }
    }
    

    public boolean reply(Object[] returned) {
        final Command command = (Command) returned[0];
        
        List<Argument> arguments = null;
        List<String> values = null;
        
        if(returned.length>1) {
            arguments = (List<Argument>) returned[1];
        }
        
        if(returned.length>2) {
            values = (List<String>) returned[2];
        }
        
        if(command.getId()!=null) {
            synchronized(clientIDs) {
                try {
                    final Socket client = clientIDs.get().entrySet().stream()
                            .filter(e->e.getKey().getId().toString().equals(command.getId().toString()))
                            .map(e->e.getValue())
                            .findFirst().orElse(null);
                    
                    final PrintWriter pw = new PrintWriter(client.getOutputStream());
                    
                    pw.println(command.getCommand()+(arguments!=null?":"+arguments.stream().map(a->a.getValue().toString()).reduce((a1,a2)->a1+","+a2).orElse(""):""));
                    pw.flush();
                    
                    if(values!=null) {
                        values.stream().forEach(v->{ pw.println(v); pw.flush(); });
                        pw.println("---end of list---");
                    } 
                    
                    return true;
                } catch (IOException ex) {
                    Logger.getLogger(ProxySocketServer.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    
                }
            }
        }
        
        return false;
    }
    
}
