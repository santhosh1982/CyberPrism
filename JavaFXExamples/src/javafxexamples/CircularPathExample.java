/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxexamples;

import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Chandramouliswaran
 */
public class CircularPathExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        //root.setCenter(centerPane);
        
        Circle circle1 = new Circle(20, Color.RED);
        Circle circle2 = new Circle(20, Color.GREEN);
        Circle circle3 = new Circle(20, Color.PINK);
        Circle circle4 = new Circle(20, Color.VIOLET);
        
//        final ArcTo arc1 = new ArcTo(400, 400, 7.0, 100, primaryStage.getHeight()/2, true, true);
//        final ArcTo arc2 = new ArcTo(300, 300, 7.0, 200, primaryStage.getHeight()/2, true, true);
//        final ArcTo arc3 = new ArcTo(200, 200, 7.0, 300, primaryStage.getHeight()/2, true, true);
//        final ArcTo arc4 = new ArcTo(100, 100, 7.0, 400, primaryStage.getHeight()/2, true, true);
        
        //createEllipsePath(200, 200, 50, 100, 45);
        final Path path1 = createEllipsePath(200, 300, 50, 50, 90,true);
        final Path path2 = createEllipsePath(200, 300, 60, 60, -90,false);
        final Path path3 = createEllipsePath(200, 300, 70, 70, 90,true);
        final Path path4 = createEllipsePath(200, 300, 80, 80, -90,false);
//        final Path path2 = new Path(new MoveTo(arc2.getX(), arc2.getY()),arc2,arc3,arc4,arc1);
//        final Path path3 = new Path(new MoveTo(arc3.getX(), arc3.getY()),arc3,arc4,arc2,arc1);
//        final Path path4 = new Path(new MoveTo(arc4.getX(), arc4.getY()),arc4,arc3,arc2,arc1);
        
        root.getChildren().addAll(circle1,circle2,circle3,circle4);
        
        Scene scene = new Scene(root, 400, 600);
        
        primaryStage.setTitle("Circular Path Example!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final PathTransition pt1 = new PathTransition();
        pt1.setDuration(Duration.millis(3000));
        pt1.setNode(circle1);
        pt1.setCycleCount(Timeline.INDEFINITE);
        pt1.setAutoReverse(true);
        pt1.setPath(path1);
        pt1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt1.setDelay(Duration.millis(100));

        final PathTransition pt2 = new PathTransition();
        pt2.setDuration(Duration.millis(3000));
        pt2.setNode(circle2);
        pt2.setCycleCount(Timeline.INDEFINITE);
        pt2.setAutoReverse(true);
        pt2.setPath(path2);
        pt2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt2.setDelay(Duration.millis(100));

        final PathTransition pt3 = new PathTransition();
        pt3.setDuration(Duration.millis(3000));
        pt3.setNode(circle3);
        pt3.setCycleCount(Timeline.INDEFINITE);
        pt3.setAutoReverse(true);
        pt3.setPath(path3);
        pt3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt3.setDelay(Duration.millis(100));

        final PathTransition pt4 = new PathTransition();
        pt4.setDuration(Duration.millis(3000));
        pt4.setNode(circle4);
        pt4.setCycleCount(Timeline.INDEFINITE);
        pt4.setAutoReverse(true);
        pt4.setPath(path4);
        pt4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt4.setDelay(Duration.millis(100));
        
//        final PathTransition pt2 = new PathTransition(Duration.millis(300), circle2);
//        pt2.setCycleCount(Timeline.INDEFINITE);
//        pt2.setAutoReverse(true);
//        pt2.setPath(path2);
//        pt2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pt2.setDelay(Duration.millis(100));
//        
//        final PathTransition pt3 = new PathTransition(Duration.millis(300), circle3);
//        pt3.setCycleCount(Timeline.INDEFINITE);
//        pt3.setAutoReverse(true);
//        pt3.setPath(path3);
//        pt3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pt3.setDelay(Duration.millis(100));
//        
//        final PathTransition pt4 = new PathTransition(Duration.millis(300), circle4);
//        pt4.setCycleCount(Timeline.INDEFINITE);
//        pt4.setAutoReverse(true);
//        pt4.setPath(path4);
//        pt4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pt4.setDelay(Duration.millis(100));
        
//        pt2.play();
//        pt3.play();
//        pt4.play();
        
        ScaleTransition sc1 = new ScaleTransition(Duration.millis(200));
        
        sc1.setNode(circle1);
        sc1.setAutoReverse(true);
        sc1.setCycleCount(Timeline.INDEFINITE);
        sc1.setRate(1.5d);
        sc1.setByZ(1.75d);
        sc1.setToZ(200.0d);
        
        
        
        pt1.play();
        pt2.play();
        pt3.play();
        pt4.play();
        
        sc1.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY, double rotate,boolean reverse) {
        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1); // to simulate a full 360 degree celcius circle.
        arcTo.setY(centerY - radiusY);
        arcTo.setSweepFlag(true);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate);

        Path path = PathBuilder.create()
                .elements(
                new MoveTo(!reverse?(centerX - radiusX):(centerX + radiusX),!reverse?(centerY - radiusY):(centerY + radiusY)),
                arcTo,
                new ClosePath()) // close 1 px gap.
                .build();
        path.setStroke(Color.DODGERBLUE);
        path.getStrokeDashArray().setAll(5d, 5d);
        return path;
    }
}
