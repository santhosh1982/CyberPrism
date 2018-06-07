/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.lanchat.fxcontrollers;

import com.cyberprism.lanchat.server.Globals;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Chandramouliswaran
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button buttonNewUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginClicked(ActionEvent event) {
        final String username = this.txtUserName.getText();
        final String password = this.txtPassword.getText();
        
        System.out.println(username+","+password);
    }

    @FXML
    private void newUserClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/newuser.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            
            Globals.globalStage.hide();
            
            Globals.globalStage.setScene(scene);
            Globals.globalStage.setOpacity(0.95d);
            Globals.globalStage.setAlwaysOnTop(true);
            Globals.globalStage.centerOnScreen();
            Globals.globalStage.setScene(scene);
            
            Globals.globalStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
