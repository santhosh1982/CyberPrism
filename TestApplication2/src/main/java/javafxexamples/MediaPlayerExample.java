/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxexamples;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaBuilder;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Chandramouliswaran
 */
public class MediaPlayerExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Create and set the Scene.
            BorderPane borderPane = new BorderPane();
            Scene scene = new Scene(borderPane, 540, 209);
            primaryStage.setScene(scene);
            
            // Name and display the Stage.
            primaryStage.setTitle("Hello Media");
            primaryStage.show();
            
            // Create the media source.
            final Media media = MediaBuilder.create().source(new File("c:\\samples\\test1.mp4").toURI().toString()).build();
            
            // Create the player and set to play automatically.
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            
            
            
            // Create the view and add it to the Scene.
            MediaView mediaView = new MediaView(mediaPlayer);
            
            final ProgressBar pb = new ProgressBar();
            
            final Text currentTime = new Text();
            
            final Slider slider = new Slider(0, 1.0, 0);
            slider.setShowTickMarks(true);
            slider.setShowTickLabels(true);
            slider.setMajorTickUnit(0.25f);
            slider.setBlockIncrement(0.1f);
            
            currentTime.setFont(new Font("Courier New",25));
            
            VBox controls = new VBox();
            
            controls.getChildren().add(currentTime);
            controls.getChildren().add(slider);
            
            borderPane.setCenter(mediaView);
            borderPane.setBottom(controls);
            
            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    slider.setMin(0.0);
                    slider.setMax(mediaPlayer.getTotalDuration().toSeconds());
                    slider.setValueChanging(true);
                }
            });
            
            slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(slider.getValue()));
                }
            });
            
            
            mediaPlayer.setAudioSpectrumListener(new AudioSpectrumListener() {
                @Override
                public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
                    currentTime.setText(""+mediaPlayer.getCurrentTime().toSeconds());
                    //slider.setValue(duration);
                }
            });
//
//            final Thread playback = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while(true) {
//                        try {
//                            currentTime.setText(""+mediaPlayer.getCurrentTime().toSeconds());
//                        } catch(Exception ex) {}
//                    }
//                }
//            });
//
//            playback.start();
            
        } catch (Exception ex) {
            Logger.getLogger(MediaPlayerExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
