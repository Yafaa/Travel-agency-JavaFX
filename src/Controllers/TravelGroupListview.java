/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.TravelGroup;
import Entities.Travelbuddy;
import Services.TravelGroupCrud;
import Services.TravelbuddyCrud;
import Views.TravelBuddyListeCell;
import Views.TravelGroupListeCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelGroupListview implements Initializable {
    @FXML
    private ListView<TravelGroup> listView;
    @FXML
    private JFXTextField searchField;
      @FXML
    private JFXButton goToAjout;

    @FXML
    private JFXButton goToListe;
    @FXML
    private ImageView home;
    
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ArrayList<TravelGroup> travelgroups = new ArrayList<>();
        try {
            travelgroups=(ArrayList<TravelGroup>) TravelGroupCrud.getAllTravelGroup();
          
         listView.setCellFactory( travelgrouplistView -> new TravelGroupListeCell() );
        } catch (SQLException ex) {
            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           ObservableList<TravelGroup> obs = FXCollections.observableArrayList(travelgroups);
         listView.setItems(obs);
         
         
         goToAjout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    goToAjout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelGroupAjout.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupListview.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         goToListe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    goToListe.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelGroupAffiche.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupListview.class.getName()).log(Level.SEVERE, null, ex);
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
        
           back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    back.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelsEntryWindow.fxml")));
                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupAfficheController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }    
        // TODO
        
    
}