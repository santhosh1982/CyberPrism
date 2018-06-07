/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfxui.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Chandramouliswaran
 */
public class Queue<T> {

    protected AtomicReference<DataCallback<T,Queue<T>>> callback = new AtomicReference<DataCallback<T, Queue<T>>>();
    protected final List<T> queue = new ArrayList<T>();

    public Queue(DataCallback<T,Queue<T>> callback) {
        this.callback.set(callback);
    }
    
    public synchronized Queue<T> enqueue(T node) {
        queue.add(node);
        callback.get().updateAdd(this, queue.size(), node);
        return this;
    }
    
    public synchronized Queue<T> enqueue(T... nodes) {
        for(T node : nodes) {
            this.enqueue(node);
        }
        return this;
    }
    
    public synchronized T dequeue() {
        if(!this.isEmpty()) {
            T node = queue.remove(0);
            callback.get().updateRemove(this, 0, node);
            return node;
        }
        return null;
    }
    
    public synchronized Queue<T> dequeue(int n) {
        if(!this.isEmpty() && queue.size()>=n) {
            Queue<T> newQueue = new Queue<T>(callback.get());
            
            while(!this.isEmpty()) {
                T node = this.dequeue();
                newQueue.enqueue(node);
            }
            
            return newQueue;
        }
        
        return new Queue<T>(callback.get());
    }
    
    public synchronized T peek() {
        if(!this.isEmpty()) {
            T node = this.queue.get(0);
            this.callback.get().updateSpy(this);
            return node;
        }
        return null;
    }
    
    public synchronized boolean isEmpty() {
        return this.queue.size()==0;
    }

    public synchronized int size() {
        return this.queue.size();
    }
    
}
