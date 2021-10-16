/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * FXML Controller class
 *
 * @author Lenovo
 */
package Controllers;


import Entities.Travelbuddy;
import Services.TravelbuddyCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.sql.Date ;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelBuddyController implements Initializable {
    @FXML
    private JFXTextField Descritpion;
    @FXML
    private JFXTextField Destination;
    @FXML
    private DatePicker getDate_debut;
    @FXML
    private DatePicker getDate_fin;
    @FXML
    private JFXButton Valider;
     
    @FXML
    private JFXButton reset;
     
    @FXML
    private ImageView home;
      @FXML
    private ImageView back;
       

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
         Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           
                int Current_User = 16 ;
                
                
                try {
                    
                    if (controleDeSaisi()){
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
                    
                    Travelbuddy t1 = new Travelbuddy(Descritpion.getText(), Destination.getText(), date_debut, date_fin,Current_User) ;
                    TravelbuddyCrud.AjouterTravelBuddy(t1);
                    Notifications.create().darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3))
                            .title("Amiticia").text("Client Ajouté avec succés").showInformation();
                    Descritpion.clear(); 
                    Destination.clear(); 
                    getDate_debut.getEditor().clear();
                    getDate_fin.getEditor().clear();} }
                   
                } catch (SQLException ex) {
                    Logger.getLogger(TravelBuddyController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                try {
                    Valider.getScene().setRoot(FXMLLoader.load(getClass().getResource("TravelBuddy.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
         
         reset.setOnAction(s -> {Descritpion.clear(); 
                    Destination.clear(); 
                    getDate_debut.getEditor().clear() ;
                    getDate_fin.getEditor().clear();}  );
         
               home.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {

     @Override
     public void handle(javafx.scene.input.MouseEvent event) {
        try {
                    home.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/Dashboard.fxml")));
//                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
                } 
     }
});
   
   back.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {

            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                try {
                    back.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelBuddyListview.fxml")));
                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });            
         
    }   
    
    private boolean controleDeSaisi() {

        if (Descritpion.getText().isEmpty() || Destination.getText().isEmpty() || (getDate_debut.getValue() == null) || (getDate_fin.getValue() ==null))
                 {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Oups! vous avez pas mis des données", "Veuillez remplir tous les champs !");
            return false;  }
           
         if(getDate_debut.getValue().isAfter(getDate_fin.getValue()) || getDate_debut.getValue().isEqual(getDate_fin.getValue())   ) {
                 
             showAlert(Alert.AlertType.ERROR, "Données erronés", "Oups! vous avez pas mis des dates erronés", "Veuillez remplir tous les champs !");
            return false ;
             } 
          
                 
  
        
        return true;
    }
    
    
       public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

   
       
}
