/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.socketapi.structures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chandramouliswaran
 */
public class Queue<T> {
    final List<T> queue = new ArrayList<T>();
    
    private Queue() {
        
    }
    
    public Queue<T> create(Class<? extends T> class1) {
        return new Queue<T>();
    }
    
    public Queue<T> queue(T node) {
        this.queue.add(node);
        return this;
    } 
    
    public T dequeue() {
        if(!this.isEmpty()) {
            return this.queue.remove(0);
        } else {
            return null;
        }
    } 
    
    public T peep() {
        if(!this.isEmpty()) {
            return this.queue.get(0);
        } else {
            return null;
        }
    }

    private boolean isEmpty() {
        return this.queue.size()==0;
    }
    
}
