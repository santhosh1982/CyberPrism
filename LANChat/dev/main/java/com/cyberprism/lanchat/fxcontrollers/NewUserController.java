/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.lanchat.fxcontrollers;

import com.cyberprism.lanchat.db.DBUtils;
import com.cyberprism.lanchat.model.User;
import com.cyberprism.lanchat.model.Userinfo;
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
public class NewUserController implements Initializable {

    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private Button buttonNewUser;
    @FXML
    private Button buttonBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void newUserClicked(ActionEvent event) {
        try {
            DBUtils.connect();
            
            User user = DBUtils.getUsers().stream().filter(u->u.getusername().equals(txtUserName.getText())).findFirst().orElse(null);
            
            if(user==null) {
                user = new User();

                user.setusername(txtUserName.getText());
                user.setpassword(txtPassword.getText());
                user.setusertype("user");
                user.setemailid("");
                user.setstatus("new");

                if(DBUtils.addUser(user)) {
                    user = DBUtils.getUsers().stream().filter(u->u.getusername().equals(txtUserName.getText())).findFirst().orElse(null);
                    
                    Userinfo userinfo = new Userinfo();
                    
                    userinfo.setfirstname(user.getusername());
                    userinfo.setlastname(user.getusername());
                    userinfo.setuserid(user.getid());
                    
                    DBUtils.addUserinfo(userinfo);
                    
                    Globals.lastMessage = "User Registered!";
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/messagebox.fxml"));
            
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/styles/Styles.css");

                    Globals.globalStage.hide();

                    Globals.globalStage.setScene(scene);
                    Globals.globalStage.setOpacity(0.95d);
                    Globals.globalStage.setAlwaysOnTop(true);
                    Globals.globalStage.centerOnScreen();

                    Globals.globalStage.show();
                } else {
                    Globals.lastMessage = "User Register Error!";
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/messagebox.fxml"));
            
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/styles/Styles.css");

                    
                    Globals.globalStage.hide();

                    Globals.globalStage.setScene(scene);
                    Globals.globalStage.setOpacity(0.95d);
                    Globals.globalStage.setAlwaysOnTop(true);
                    Globals.globalStage.centerOnScreen();
                    Globals.globalStage.setScene(scene);

                    Globals.globalStage.show();
                }
            } else {
                    Globals.lastMessage = "User Allready Exists!";
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/messagebox.fxml"));
            
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/styles/Styles.css");

                    
                    
                    Globals.globalStage.hide();

                    Globals.globalStage.setScene(scene);
                    Globals.globalStage.setOpacity(0.95d);
                    Globals.globalStage.setAlwaysOnTop(true);
                    Globals.globalStage.centerOnScreen();

                    Globals.globalStage.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            
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
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
