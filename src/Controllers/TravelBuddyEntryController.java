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

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelBuddyEntryController implements Initializable {

    @FXML
    private JFXButton JaiCompris;
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
                    JaiCompris.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelBuddyListview.fxml")));
                        
                    System.out.println("Worked");
                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyEntryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
    }    
    
}
