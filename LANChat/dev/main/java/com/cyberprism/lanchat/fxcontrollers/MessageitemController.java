/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberprism.lanchat.fxcontrollers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author Chandramouliswaran
 */
public class MessageitemController implements Initializable {

    @FXML
    private MenuBar messageMenuBar;
    @FXML
    private Button buttonSend;
    @FXML
    private HTMLEditor messageHTMLEditor;
    @FXML
    private ImageView imageViewEmoji;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void messageDragEntered(DragEvent event) {
    }

    @FXML
    private void messageContextMenuRequested(ContextMenuEvent event) {
    }

    @FXML
    private void messageDragDetected(MouseEvent event) {
    }

    @FXML
    private void messageDragDone(DragEvent event) {
    }

    @FXML
    private void messageDragExited(DragEvent event) {
    }

    @FXML
    private void messageDragOver(DragEvent event) {
    }

    @FXML
    private void messageKeyReleased(KeyEvent event) {
    }

    @FXML
    private void messageDragDroped(DragEvent event) {
    }

    @FXML
    private void messageKeyTyped(KeyEvent event) {
    }

    @FXML
    private void messageSave(ActionEvent event) {
    }

    @FXML
    private void messageDelete(ActionEvent event) {
    }

    @FXML
    private void messageCollapse(ActionEvent event) {
    }

    @FXML
    private void messageExpand(ActionEvent event) {
    }

    @FXML
    private void messageForward(ActionEvent event) {
    }

    @FXML
    private void sendClick(ActionEvent event) {
    }

    @FXML
    private void showEmoji(MouseEvent event) {
    }

    @FXML
    private void selectEmoji(MouseEvent event) {
    }
    
}
