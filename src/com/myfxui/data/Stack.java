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
public class Stack<T> {

    protected AtomicReference<DataCallback<T,Stack<T>>> callback = new AtomicReference<DataCallback<T, Stack<T>>>();
    protected final List<T> stack = new ArrayList<T>();

    public Stack(DataCallback<T,Stack<T>> callback) {
        this.callback.set(callback);
    }
    
    public synchronized Stack<T> push(T node) {
        stack.add(0,node);
        callback.get().updateAdd(this, 0, node);
        return this;
    }
    
    public synchronized Stack<T> push(T... nodes) {
        for(T node : nodes) {
            this.push(node);
        }
        return this;
    }
    
    public synchronized T pop() {
        if(!this.isEmpty()) {
            T node = stack.remove(0);
            callback.get().updateRemove(this, 0, node);
            return node;
        }
        return null;
    }
    
    public synchronized Stack<T> pop(int n) {
        if(!this.isEmpty() && stack.size()>=n) {
            Stack<T> newStack = new Stack<T>(callback.get());
            
            while(!this.isEmpty()) {
                T node = this.pop();
                newStack.push(node);
            }
            
            return newStack;
        }
        
        return new Stack<T>(callback.get());
    }
    
    public synchronized T peek() {
        if(!this.isEmpty()) {
            T node = this.stack.get(0);
            this.callback.get().updateSpy(this);
            return node;
        }
        return null;
    }
    
    public synchronized boolean isEmpty() {
        return this.stack.size()==0;
    }

    public synchronized int size() {
        return this.stack.size();
    }
    
}
