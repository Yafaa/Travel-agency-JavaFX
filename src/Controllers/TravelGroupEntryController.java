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
public class TravelGroupEntryController implements Initializable {
 
    @FXML
    private JFXButton jaiCompris;

    @FXML
    private JFXButton reteur;
    
    @FXML
    private ImageView back;
    
    @FXML
    private ImageView home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
          jaiCompris.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    jaiCompris.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelGroupListview.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupEntryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
          
          reteur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    reteur.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/Dashboard.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupEntryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }    
    
}
