/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfxui;

import com.myfxui.transitions.RectangleSpyTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author Chandramouliswaran
 */
public abstract class FXUINode {

    public static Transition[] createFXUIRemoveTransition(Shape shape1,Shape shape2,Paint fill1,Paint fill2,int cycleCount) {
        ScaleTransition st1 = new ScaleTransition();
        
        st1.setDuration(Duration.millis(400));
        st1.setAutoReverse(false);
        st1.setCycleCount(1);
        st1.setByX(0.5d);
        
        st1.setFromX(1.0);
        st1.setToX(0.0);

        st1.setRate(0.75d);
        
        st1.setInterpolator(Interpolator.TANGENT(Duration.millis(400), 0.5d));

        st1.setNode(shape1);
        
        st1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        ScaleTransition st2 = new ScaleTransition();
        
        st2.setDuration(Duration.millis(400));
        st2.setAutoReverse(false);
        st2.setCycleCount(1);
        st2.setByY(0.5d);
        
        st2.setFromY(1.0);
        st2.setToY(0.0);

        st2.setRate(0.75d);
        
        st2.setInterpolator(Interpolator.TANGENT(Duration.millis(400), 0.5d));

        st2.setNode(shape1);
        
        st2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        ScaleTransition st3 = new ScaleTransition();
        
        st3.setDuration(Duration.millis(1000));
        st3.setAutoReverse(false);
        st3.setCycleCount(1);
        st3.setByX(0.5d);
        
        st3.setFromX(1.0);
        st3.setToX(0.0);

        st3.setRate(0.75d);
        
        st3.setInterpolator(Interpolator.TANGENT(Duration.millis(400), 0.5d));

        st3.setNode(shape2);
        
        st3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        ScaleTransition st4 = new ScaleTransition();
        
        st4.setDuration(Duration.millis(400));
        st4.setAutoReverse(false);
        st4.setCycleCount(1);
        st4.setByY(0.5d);
        
        st4.setFromY(1.0);
        st4.setToY(0.0);

        st4.setRate(0.75d);
        
        st4.setInterpolator(Interpolator.TANGENT(Duration.millis(400), 0.5d));

        st4.setNode(shape2);
        
        st4.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        SequentialTransition seq1 = new SequentialTransition(st1,st2,st3,st4);
        
        seq1.setAutoReverse(false);
        seq1.setCycleCount(cycleCount);
        
        seq1.play();
        
        return new Transition[] { seq1 };
    }
    
    public static Transition[] createFXUIRectangleSpyTransition(Rectangle shape1,Rectangle shape2,Paint fill1,Paint fill2,int cycleCount) {
        RectangleSpyTransition spyTransition = new RectangleSpyTransition(Duration.millis(1000), shape1, 100);
        
        spyTransition.setCycleCount(3);
        spyTransition.setAutoReverse(false);
        spyTransition.play();
        
        RectangleSpyTransition spyTransition2 = new RectangleSpyTransition(Duration.millis(1000), shape2, 100);
        
        spyTransition2.setCycleCount(3);
        spyTransition2.setAutoReverse(false);
        spyTransition2.play();
        
        return new Transition[] { spyTransition, spyTransition2 };
    }
    
    public static Transition[] createFXUITransition(Shape shape1,Shape shape2,Paint fill1,Paint fill2,int cycleCount) {
        
        ScaleTransition st1 = new ScaleTransition();
        
        st1.setDuration(Duration.millis(1000));
        st1.setAutoReverse(true);
        st1.setCycleCount(cycleCount);
        st1.setByX(0.2d);
        st1.setByY(0.2d);
        
        st1.setFromX(0.0);
        st1.setFromY(0.0);
        
        st1.setToX(1.0);
        st1.setToY(1.0);

        st1.setRate(0.75d);
        
        st1.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));

        st1.setNode(shape2);
        
        st1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                st1.stop();
            }
        });
        
        st1.play();
        
        ScaleTransition st2 = new ScaleTransition();
        
        st2.setDuration(Duration.millis(1000));
        st2.setAutoReverse(true);
        st2.setCycleCount(cycleCount);
        st2.setByX(0.25d);
        st2.setByY(0.25d);
        
        st2.setFromX(1.0);
        st2.setFromY(1.0);
        
        st2.setToX(0.85);
        st2.setToY(0.85);

        st2.setRate(0.75d);
        
        st2.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));

        st2.setNode(shape1);
        
        st2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                st2.stop();
            }
        });
        
        
        st2.play();
        
        FadeTransition ft = new FadeTransition();
        
        ft.setDuration(Duration.millis(1000));
        ft.setAutoReverse(true);
        ft.setCycleCount(cycleCount);
        ft.setFromValue(0.0);
        ft.setToValue(0.75d);
        ft.setByValue(0.2d);
        ft.setNode(shape1);
        
        ft.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));
        
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ft.stop();
            }
        });
        
        ft.play();
        
        
        FadeTransition ft2 = new FadeTransition();
        
        ft2.setDuration(Duration.millis(1000));
        ft2.setAutoReverse(true);
        ft2.setCycleCount(cycleCount);
        ft2.setFromValue(1.75d);
        ft2.setToValue(0.0d);
        ft2.setByValue(0.02d);
        ft2.setNode(shape2);
        
        ft2.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));
        
        ft2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ft2.stop();
            }
        });
        
        ft2.play();

        return new Transition[] { st1, st2, ft, ft2 };
    }
    
    public static Transition[] createFXUITransition(Shape shape1,Shape shape2,Paint fill1,Paint fill2,Paint fill3,Paint fill4,int cycleCount) {
        
        ScaleTransition st1 = new ScaleTransition();
        
        st1.setDuration(Duration.millis(1000));
        st1.setAutoReverse(true);
        st1.setCycleCount(1);
        st1.setByX(0.2d);
        st1.setByY(0.2d);
        
        st1.setFromX(0.0);
        st1.setFromY(0.0);
        
        st1.setToX(1.0);
        st1.setToY(1.0);

        st1.setRate(0.75d);
        
        st1.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));

        st1.setNode(shape2);
        
        st1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        
        ScaleTransition st2 = new ScaleTransition();
        
        st2.setDuration(Duration.millis(1000));
        st2.setAutoReverse(true);
        st2.setCycleCount(1);
        st2.setByX(0.25d);
        st2.setByY(0.25d);
        
        st2.setFromX(1.0);
        st2.setFromY(1.0);
        
        st2.setToX(0.85);
        st2.setToY(0.85);

        st2.setRate(0.75d);
        
        st2.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));

        st2.setNode(shape1);
        
        st2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        
        
        FadeTransition ft = new FadeTransition();
        
        ft.setDuration(Duration.millis(1000));
        ft.setAutoReverse(true);
        ft.setCycleCount(1);
        ft.setFromValue(0.0);
        ft.setToValue(0.75d);
        ft.setByValue(0.2d);
        ft.setNode(shape1);
        
        ft.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));
        
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        
        
        FadeTransition ft2 = new FadeTransition();
        
        ft2.setDuration(Duration.millis(1000));
        ft2.setAutoReverse(true);
        ft2.setCycleCount(1);
        ft2.setFromValue(1.75d);
        ft2.setToValue(0.0d);
        ft2.setByValue(0.02d);
        ft2.setNode(shape2);
        
        ft2.setInterpolator(Interpolator.TANGENT(Duration.millis(1000), 0.5d));
        
        ft2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        ParallelTransition pr1 = new ParallelTransition(st1,st2,ft,ft2);
        pr1.setAutoReverse(false);
        pr1.setCycleCount(cycleCount);
        
        pr1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pr1.stop();
                
                shape1.setFill(fill4);
                shape2.setFill(fill3);
            }
        });
        
        pr1.play();

        return new Transition[] { st1, st2, ft, ft2 , pr1 };
    }
    
}
