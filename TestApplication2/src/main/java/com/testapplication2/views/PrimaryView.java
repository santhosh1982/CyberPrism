package com.testapplication2.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.myfxui.data.Queue;
import com.myfxui.structures.FXUIQueue;
import com.myfxui.structures.FXUIStack;
import com.testapplication2.TestApplication2;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafxexamples.DataStructuresExample;

public class PrimaryView extends View {

    public PrimaryView(String name) {
        super(name);
        
        getStylesheets().add(PrimaryView.class.getResource("primary.css").toExternalForm());

        BorderPane pane1 = new BorderPane();
        BorderPane pane2 = new BorderPane();
        final BorderPane pane3 = new BorderPane();
        BorderPane pane4 = new BorderPane();
        
        ScrollPane pane11 = new ScrollPane(pane1);
        ScrollPane pane12 = new ScrollPane(pane2);
        ScrollPane pane13 = new ScrollPane(pane3);
        ScrollPane pane14 = new ScrollPane(pane4);
        
        pane11.setPannable(true);
        pane12.setPannable(true);
        pane13.setPannable(true);
        pane14.setPannable(true);
        
//        pane11.vvalueProperty().bind(pane1.heightProperty());
//        pane12.vvalueProperty().bind(pane2.heightProperty());
//        
        final HBox root = new HBox();
        
        root.getChildren().add(pane11);
        root.getChildren().add(pane12);
        root.getChildren().add(pane13);
        root.getChildren().add(pane14);
        
        final FXUIQueue<String> queue1 = new FXUIQueue<String>(FXUIQueue.QUEUEVIS_VERTICAL,800,600,pane1);
        final FXUIQueue<String> queue2 = new FXUIQueue<String>(FXUIQueue.QUEUEVIS_VERTICAL,800,600,pane2);
        final FXUIStack<String> stack1 = new FXUIStack<String>(FXUIStack.STACKVIS_VERTICAL,800,600,pane4);
        
        final Thread myThread = new Thread(new Runnable() {
            
            FXUIQueue<String> queue3 = null;
                    
            @Override
            public void run() {
                int i=1;
                
                boolean created = false;
                
                while(true) {
                    queue1.getQueue().enqueue("Queue 1 "+i);
                    stack1.getStack().push("Stack 1 "+i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DataStructuresExample.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    queue2.getQueue().enqueue("Queue 2 "+i++);
//                    queue1.getStack().pop();
//                    queue2.getStack().pop();

                    
                    if(i%5==0) {
                        stack1.getStack().peek();
                        if(!created) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            queue3 = new FXUIQueue<String>(FXUIQueue.QUEUEVIS_VERTICAL,800,600,pane3);
                                        } catch(Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                });
                                created=true;
                                continue;
                        }

                        if(created) {
                            int n = 5;
                            
                            if(queue1.getQueue().size()>n) {
                                Queue<String> q1 = queue1.getQueue().dequeue(n);

                                while(!q1.isEmpty()) {
                                    String node = q1.dequeue();
                                    queue3.getQueue().enqueue(node);
                                }
                            }
                        }
                        
                    }
                }
            }
        });
        
        myThread.start();
        setCenter(root);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().showLayer(TestApplication2.MENU_LAYER)));
        appBar.setTitleText("Primary");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }
    
}
