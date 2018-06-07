/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfxui;

import javafx.animation.Timeline;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author Chandramouliswaran
 */
public class FXUICircle {
    
    private static double glow_level=20.5d;
    private static double bloom_radius=80.0d;
    private static double outer_stroke_width=7.0d;
    private static double inner_stroke_width=5.0d;
    
    private static double outer_circle_radius=20;
    private static double inner_circle_radius=10;
    
 
    public static StackPane createStaticCircle(Paint fill1,Paint fill2) {
        Glow glow = new Glow(glow_level);
        glow.setInput(new Bloom(bloom_radius));
        
        Circle circle11 = new Circle(outer_circle_radius, fill1);
        
        circle11.setFill(fill1);
        circle11.setStrokeWidth(outer_stroke_width);
        circle11.setStroke(Color.WHITE);
        circle11.setEffect(glow);
        
        StackPane stackPane = new StackPane(circle11);
        
        return stackPane;
    }
    
    public static StackPane createAnimatedCircle(Paint fill1,Paint fill2) {
        Circle circle11 = new Circle(outer_circle_radius, fill1);
        Circle circle12 = new Circle(inner_circle_radius, fill2);
        
        Glow glow = new Glow(glow_level);
        glow.setInput(new Bloom(bloom_radius));
        
        circle11.setFill(fill1);
        circle11.setStrokeWidth(outer_stroke_width);
        circle11.setStroke(Color.WHITE);
        circle11.setEffect(glow);
        
        circle12.setFill(fill2);
        circle12.setStrokeWidth(inner_stroke_width);
        circle12.setStroke(Color.BLACK);
        circle12.setEffect(glow);
        
        FXUINode.createFXUITransition(circle11, circle12, circle11.getFill(),circle12.getFill(),Timeline.INDEFINITE);
        
        StackPane stackPane = new StackPane(circle11, circle12);
        
        return stackPane;
    }

    public static StackPane createSpotedCircle(Paint fill1,Paint fill2) {
        Circle circle11 = new Circle(outer_circle_radius, fill1);
        Circle circle12 = new Circle(inner_circle_radius-2, fill2);
        
        Glow glow = new Glow(glow_level);
        glow.setInput(new Bloom(bloom_radius));

        
        circle11.setFill(fill1);
        circle11.setStrokeWidth(outer_stroke_width/2);
        circle11.setStroke(Color.WHITE);
        circle11.setEffect(glow);
        
        circle12.setFill(Color.WHITE);
        circle12.setStrokeWidth(inner_stroke_width/2);
        circle12.setStroke(fill2);
        //circle12.setEffect(new Glow(20.5d));
        
        StackPane stackPane = new StackPane(circle11,circle12);
        
        return stackPane;
    }
    
}
