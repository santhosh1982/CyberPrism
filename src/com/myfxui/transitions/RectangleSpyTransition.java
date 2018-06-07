/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfxui.transitions;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Chandramouliswaran
 */
public class RectangleSpyTransition extends Transition {
    
    protected Rectangle region;
    protected double startHeight;
    protected double newHeight;
    protected double heightDiff;
    
    public RectangleSpyTransition(Duration duration, Rectangle region, double newHeight ) {
        setCycleDuration(duration);
        this.region = region;
        this.newHeight = newHeight;
        this.startHeight = region.getHeight();
        this.heightDiff = newHeight - startHeight;
    }
    
    @Override
    protected void interpolate(double fraction) {
        region.setHeight(startHeight + ( heightDiff * fraction ) );
    }
}