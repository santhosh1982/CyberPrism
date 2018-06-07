/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.lanchat.fxcontrollers;

import com.cyberprism.lanchat.server.Globals;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Chandramouliswaran
 */
public class MessageboxController implements Initializable {

    @FXML
    private TextArea txtMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMessage.setText(Globals.lastMessage);
    }    
    
}
