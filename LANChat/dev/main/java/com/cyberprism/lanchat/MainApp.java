package com.cyberprism.lanchat;

import com.cyberprism.lanchat.server.Globals;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Globals.globalStage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setScene(scene);
        stage.setOpacity(0.95d);
        stage.setAlwaysOnTop(true);
        stage.centerOnScreen();
        stage.show();
        
        LANChatServer.main(new String[] {});
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
