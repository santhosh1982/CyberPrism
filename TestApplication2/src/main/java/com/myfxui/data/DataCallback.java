/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfxui.data;

/**
 *
 * @author Chandramouliswaran
 */
public interface DataCallback<T,D> {
    boolean updateAdd(D struct,int index,T node);
    boolean updateRemove(D struct,int index,T node);
    boolean updateIsEmpty(D struct);
    boolean updateSpy(D struct);
}
