/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxexamples;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Chandramouliswaran
 */
public class JavaFXExamples extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        final Text text = new Text("This is the message");
        
        final Rectangle rect = new Rectangle(100, 100);
        rect.setFill(new Color(0, 0, 1.0, 0.5));
        
        final Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            private int count = 0;
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                
                text.setText("You Clicked - "+(++count));
            }
        });
        
        
        

        
        FlowPane root = new FlowPane(Orientation.VERTICAL);
//        root.setEffect(new ColorInput(0, 0, primaryStage.getWidth(), primaryStage.getHeight(), new Color(1.0, 0, 0, 0.0)));
        
        root.getChildren().add(btn);
        root.getChildren().add(text);
        root.getChildren().add(rect);
        
        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        FadeTransition ft = new FadeTransition(Duration.millis(3000), rect);
//        ft.setFromValue(1.0);
//        ft.setToValue(0.1);
//        ft.setCycleCount(Timeline.INDEFINITE);
//        ft.setAutoReverse(true);
//        ft.play();
        
//        FadeTransition ft2 = new FadeTransition(Duration.millis(3000), btn);
//        ft2.setFromValue(0.1);
//        ft2.setToValue(1.0);
//        ft2.setCycleCount(Timeline.INDEFINITE);
//        ft2.setAutoReverse(true);
//        ft2.play();
        
        final TranslateTransition t1 = new TranslateTransition(Duration.millis(1000), rect);
        t1.setInterpolator(Interpolator.EASE_OUT);
        t1.setToX(primaryStage.getWidth());
        t1.setByX(100);
        
        final TranslateTransition t2 = new TranslateTransition(Duration.millis(1000), rect);
        t2.setInterpolator(Interpolator.EASE_IN);
        t2.setToY(primaryStage.getHeight());
        t2.setByY(100);

        final TranslateTransition t3 = new TranslateTransition(Duration.millis(1000), rect);
        t3.setInterpolator(Interpolator.EASE_OUT);
        t3.setToX(0);
        t3.setByX(100);

        final TranslateTransition t4 = new TranslateTransition(Duration.millis(1000), rect);
        t4.setInterpolator(Interpolator.EASE_IN);
        t4.setToY(0);
        t4.setByY(100);
        
//        translate.setInterpolator(new Interpolator() {
//            @Override
//            protected double curve(double t) {
//                return Math.abs(0.5-t)*2; 
//            }
//        });
//        translate.play();


        final SequentialTransition seq = new SequentialTransition();
        seq.setCycleCount(Timeline.INDEFINITE);
        seq.setAutoReverse(true);
        seq.getChildren().addAll(t1,t2,t3,t4);

        seq.play();

//        primaryStage.widthProperty().addListener(new ChangeListener<Number>(){
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                t1.setToX(newValue.doubleValue());
//            }
//        });

//    final Timeline timeline = new Timeline();
//
//    timeline.setCycleCount(Timeline.INDEFINITE);
//    timeline.setAutoReverse(true);
//    
//    final KeyValue keyValue1 = new KeyValue(rect.translateXProperty(), primaryStage.getWidth());
//    final KeyValue keyValue2 = new KeyValue(rect.translateYProperty(), primaryStage.getHeight(), Interpolator.EASE_BOTH);
//    final KeyValue keyValue3 = new KeyValue(rect.translateXProperty(), 0, Interpolator.EASE_BOTH);
//    final KeyValue keyValue4 = new KeyValue(rect.translateYProperty(), 0, Interpolator.EASE_BOTH);
//    final KeyFrame keyFrame1 = new KeyFrame(Duration.millis(3000), keyValue1,keyValue2,keyValue3,keyValue4);
////    final KeyFrame keyFrame2 = new KeyFrame(Duration.millis(6000), keyValue2);
////    final KeyFrame keyFrame3 = new KeyFrame(Duration.millis(9000), keyValue3);
////    final KeyFrame keyFrame4 = new KeyFrame(Duration.millis(11000), keyValue4);
//    
//    timeline.getKeyFrames().addAll(keyFrame1);
////    timeline.getKeyFrames().add(keyFrame2);
////    timeline.getKeyFrames().add(keyFrame3);
////    timeline.getKeyFrames().add(keyFrame4);
//
//    timeline.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Timeline Finished!");
//            }
//        });
//    
//    timeline.play();
        
        
//        final Thread changeThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while(true) {
//                    if(i%2==0) {
//                        //ft.setNode(text);
//                        ft2.stop();
//                        ft2.setNode(text);
//                        ft2.play();
//                        //ft2.setNode(rect);
//                    } else {
//                        ft2.stop();
//                        ft2.setNode(btn);
//                        ft2.play();
//                    }
//                    
//                    i++;
//
//                    try {
//                        Thread.sleep(6000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(JavaFXExamples.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        });
//        
//        changeThread.start();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
