/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxexamples;

import com.myfxui.data.Queue;
import com.myfxui.structures.FXUIQueue;
import com.myfxui.structures.FXUIStack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Chandramouliswaran
 */
public class DataStructuresExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
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
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Data Structures Example");
        primaryStage.setScene(scene);
        primaryStage.show();
        
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

//        queue1.getStack().push("Stack 1 0");        
//        queue1.getStack().push("Stack 1 1");        
//        queue1.getStack().push("Stack 1 2");        
//        queue1.getStack().push("Stack 1 3");        
//        queue1.getStack().push("Stack 1 4");        
//        
//        queue2.getStack().push("Stack 2 0");        
//        queue2.getStack().push("Stack 2 1");        
//        queue2.getStack().push("Stack 2 2");        
//        queue2.getStack().push("Stack 2 3");        
//        queue2.getStack().push("Stack 2 4");        
//        
//        
//        queue1.getStack().pop();
//        queue1.getStack().pop();

//        stack1.getStack().push("Stack 1");
//        stack1.getStack().push("Stack 2");
//        stack1.getStack().push("Stack 3");
//        stack1.getStack().push("Stack 4");
//        stack1.getStack().push("Stack 5");
          
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(DataStructuresExample.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        stack1.getStack().pop();
//        stack1.getStack().pop();
        
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(DataStructuresExample.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        stack1.getStack().peek();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
