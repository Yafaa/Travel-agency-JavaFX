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
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelsEntryWindowController implements Initializable {
    @FXML
    private JFXButton goToGroup;

    @FXML
    private JFXButton goToBuddy;

    @FXML
    private ImageView home;

 

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
           goToGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    goToGroup.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelGroupEntry.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelsEntryWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
           
           goToBuddy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    goToBuddy.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelBuddyEntry.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelsEntryWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
           
          
           
      
           
              home.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {

     @Override
     public void handle(javafx.scene.input.MouseEvent event) {
        try {
                    home.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/Dashboard.fxml")));
//                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyController.class.getName()).log(Level.SEVERE, null, ex);
                } 
     }
}); 
           
    }    
    
}
