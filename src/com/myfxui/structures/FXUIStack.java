/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myfxui.structures;

import com.myfxui.FXUILabeledRectangle;
import com.myfxui.FXUINode;
import com.myfxui.FXUIRectangle;
import com.myfxui.data.DataCallback;
import com.myfxui.data.Stack;
import com.myfxui.threads.FXUIThreadProcessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 *
 * @author Chandramouliswaran
 */
public class FXUIStack<T> {
    public static final int STACKVIS_HORIZONTAL=0;
    public static final int STACKVIS_VERTICAL=1;
    
    private Stack<T> stack;
    private Pane pane;
    private int type;
    
    private Pane innerPane;
    private FXUIThreadProcessor threadProcessor;
    
    private List<Shape[]> queueContent = new ArrayList<Shape[]>();
    
    public FXUIStack(int type, int width,int height,Pane pane) {
        
        this.pane = pane;
        this.type = type;
        
        this.threadProcessor = new FXUIThreadProcessor();
        this.threadProcessor.start();
        
        if(type==STACKVIS_HORIZONTAL) {
            this.innerPane = new HBox();
            ((HBox) this.innerPane).setSpacing(25);
        } else if(type==STACKVIS_VERTICAL) {
            this.innerPane = new VBox();
            ((VBox) this.innerPane).setSpacing(25);
        }
        
        ((BorderPane) this.pane).setCenter(this.innerPane);
        
        this.stack = new Stack<T>(new DataCallback<T, Stack<T>>() {
            @Override
            public boolean updateAdd(Stack<T> struct, int index, T node) {
                threadProcessor.queueTask(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                StackPane st = FXUILabeledRectangle.createAnimatedRectangle(node.toString(),Color.BLUEVIOLET, Color.CHARTREUSE,3);
                                queueContent.add(0,Arrays.copyOf(FXUILabeledRectangle.lastShapes,FXUILabeledRectangle.lastShapes.length));
                                innerPane.getChildren().add(0, st);
                            }
                        });
                    }
                });
                
                return true;
            }

            @Override
            public boolean updateRemove(Stack<T> struct, int index, T node) {
                threadProcessor.queueTask(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("Stack Content - "+queueContent.size());
                                    System.out.println("Stack Index - "+index);

                                    Transition[] ts = FXUINode.createFXUIRemoveTransition(queueContent.get(index)[0], queueContent.get(index)[1], Color.WHITE, Color.BLACK, 3);

                                    for(Transition tr : ts) {
                                        tr.setOnFinished(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                queueContent.remove(index);
                                                innerPane.getChildren().remove(index);
                                            }
                                        });
                                    }
                                } catch(Exception ex) {
                                }
                            }
                        });
                    }
                });
                
                return true;
            }

            @Override
            public boolean updateIsEmpty(Stack<T> struct) {
                return false;
            }

            @Override
            public boolean updateSpy(Stack<T> struct) {
                try {
//                    while(queueContent.size()==0)
//                        ;
                    
                    Transition[] ts = FXUINode.createFXUIRectangleSpyTransition((Rectangle)(queueContent.get(0)[0]), (Rectangle)(queueContent.get(0)[1]), Color.RED, Color.CORAL, 3);
                } catch(Exception ex) {
                    
                }
                return false;
            }
        });
    }
    
    public synchronized Stack<T> getStack() {
        return this.stack;
    }
}
