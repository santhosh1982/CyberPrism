/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfxui.threads;

import com.myfxui.data.DataCallback;
import com.myfxui.data.Queue;
import com.myfxui.data.Queue;

/**
 *
 * @author Chandramouliswaran
 */
public class FXUIThreadProcessor {
    
    private Queue<Runnable> tasks = new Queue<Runnable>(new DataCallback<Runnable, Queue<Runnable>>() {
        @Override
        public boolean updateAdd(Queue<Runnable> struct, int index, Runnable node) {
            return true;
        }

        @Override
        public boolean updateRemove(Queue<Runnable> struct, int index, Runnable node) {
            return true;
        }

        @Override
        public boolean updateIsEmpty(Queue<Runnable> struct) {
            return true;
        }

        @Override
        public boolean updateSpy(Queue<Runnable> struct) {
            return true;
        }
    });
    
    public synchronized void queueTask(Runnable run) {
        this.tasks.enqueue(run);
    } 
    
    public synchronized Runnable dequeuTask() {
        return this.tasks.dequeue();
    }
    
    public void start() {
        final Thread tasksThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                while(true) {
                    while(!tasks.isEmpty()) {
                        final Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    dequeuTask().run();
                                } catch(NullPointerException ex) {
                                    
                                }
                            }
                        });
                        
                        thread.start();
                    }
                }
            }
        });
        
        tasksThread.start();
    }
}
