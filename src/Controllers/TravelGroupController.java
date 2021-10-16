/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import static Controllers.TravelBuddyController.showAlert;
import Entities.TravelGroup;
import Entities.Travelbuddy;
import Services.TravelGroupCrud;
import Services.TravelbuddyCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import java.sql.Date ;
import java.util.Optional;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelGroupController implements Initializable {

    @FXML
    private JFXTextField destination;

    @FXML
    private JFXButton Valider;

    @FXML
    private DatePicker getDate_fin;

    @FXML
    private JFXTextField title;

    @FXML
    private DatePicker getDate_debut;

    @FXML
    private JFXTextArea plan;
     @FXML
    private JFXButton reset;
    
    @FXML    private ImageView back;
    
    @FXML
    private ImageView home;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            int Current_user = 16 ;
                
                try {
                    
                    java.sql.Date date_debut = java.sql.Date.valueOf(getDate_debut.getValue());
                    java.sql.Date date_fin = java.sql.Date.valueOf(getDate_fin.getValue());
                    
                    if (date_debut.after(date_fin)) {
                        
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisi");
                alert.setHeaderText(null);
                alert.setContentText("vous avez saisi des dates invalide ");

                 alert.show();
                
                    }
                    else {
                    
                    Travelbuddy t2 = new Travelbuddy("ds", "sds", date_debut, date_fin, Current_user) ;  //Need add current session here//
                    TravelGroup t1 = new TravelGroup(title.getText(), destination.getText(), date_debut, date_fin,plan.getText()) ;
                    TravelGroupCrud.AjouterTravelGroup(t1,t2.getId() ); //PUT JAMILA SESSION ON SECOND PARAMTER//
                    Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(5))
                            .title("Amiticia").text("Travel group Ajouté avec succés").showInformation();
                    title.clear();
                    destination.clear();
                    getDate_debut.getEditor().clear();
                    getDate_fin.getEditor().clear();
                    plan.clear(); }
                     
                } catch (SQLException ex) {
                    Logger.getLogger(TravelBuddyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    Valider.getScene().setRoot(FXMLLoader.load(getClass().getResource("TravelGroupListview.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
         
         
         reset.setOnAction(s -> {title.clear(); 
                    destination.clear(); 
                    getDate_debut.getEditor().clear() ;
                    getDate_fin.getEditor().clear() ;
                    plan.clear();       ;}  );
         
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
               back.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {

     @Override
     public void handle(javafx.scene.input.MouseEvent event) {
        try {
                    back.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelGroupListview.fxml")));
//                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyController.class.getName()).log(Level.SEVERE, null, ex);
                } 
     }
});
    }   
  
    
    private boolean controleDeSaisi() {

        if (title.getText().isEmpty() || destination.getText().isEmpty() || (getDate_debut.getValue() == null) || (getDate_fin.getValue() == null) || (plan.getText() == null)) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Oups! vous avez pas mis des données", "Veuillez remplir tous les champs !");
            return false;
        }

        if(getDate_debut.getValue().isAfter(getDate_fin.getValue()) || getDate_debut.getValue().isEqual(getDate_fin.getValue())   ) {
                 
             showAlert(Alert.AlertType.ERROR, "Données erronés", "Oups! vous avez pas mis des dates erronés", "Veuillez remplir tous les champs !");
            return false ;
             } 
        
        return true;
        
        
        
    }
     
         
    } 
    

