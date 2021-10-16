/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class DashboardController implements Initializable {

    @FXML
    private Button JaiCompris;
       
    @FXML
    private JFXTextField login;
    
    
    @FXML
    private Label lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JaiCompris.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    if(login.getText().equals("yafaa"))
                    JaiCompris.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelsEntryWindow.fxml")));
                    else
                      lab.setText("Invalid username or password");
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyEntryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
   
    
 
    }
}