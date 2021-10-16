/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entities.Travelbuddy ;
import Services.TravelbuddyCrud;
import Views.TravelBuddyListeCell;
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
public class TravelBuddyListviewController implements Initializable {

    
    
    
    @FXML
    private ListView<Travelbuddy> listView;
    
     @FXML
    private JFXTextField searchField;
     @FXML
    private JFXButton devenirTravel;

    @FXML
    private JFXButton dashboard;
    @FXML
    private ImageView home;
    
    @FXML
    private ImageView back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<Travelbuddy> travelbuddys = new ArrayList<>();
        try {
            travelbuddys=(ArrayList<Travelbuddy>) TravelbuddyCrud.getAllTravelbuddy();
          
         listView.setCellFactory( travelbuddylistView -> new TravelBuddyListeCell() );
        } catch (SQLException ex) {
            Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           ObservableList<Travelbuddy> obs = FXCollections.observableArrayList(travelbuddys);
         listView.setItems(obs);
         
         devenirTravel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    devenirTravel.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelBuddyAjout.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         dashboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    dashboard.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelBuddyListe.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
        
        home.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

     @Override
     public void handle(MouseEvent event) {
        try {
                    home.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/Dashboard.fxml")));
//                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
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
       
    
    
}
