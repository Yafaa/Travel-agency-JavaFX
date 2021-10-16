/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Controllers.ChatRoom.GUIClient;
import Controllers.ChatRoom.GUIServer;
import Entities.TravelGroup;
import Entities.Travelbuddy;
import Services.TravelGroupCrud;
import Services.TravelbuddyCrud;
import Entities.Travelbuddy;
import Services.TravelbuddyCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelGroupAfficheController implements Initializable {

    @FXML
    private TableColumn<TravelGroup, Integer> id_tavelbuddy;

    @FXML
    private TableColumn<TravelGroup, String> title;

    @FXML
    private TableColumn<TravelGroup, String> destination;

    @FXML
    private TableColumn<TravelGroup, Date> getDate_fin;

    @FXML
    private TableColumn<TravelGroup, Date> getDate_debut;
    @FXML
    private TableColumn<TravelGroup, String> plan;
    @FXML
    private TableView<TravelGroup> table;
   
    @FXML
    private ImageView home;
        @FXML
    private ImageView back;
    
    int Current_user = 16 ;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        List<TravelGroup> travelgroups = new ArrayList<>();
        try {
            travelgroups = TravelGroupCrud.getAllTravelGroup();
        } catch (SQLException ex) {
            Logger.getLogger(TravelGroupAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<TravelGroup> obsl = FXCollections.observableList(travelgroups);
        table.setItems(obsl);
        id_tavelbuddy.setCellValueFactory(new PropertyValueFactory<>("id_travel_group"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        getDate_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        getDate_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        plan.setCellValueFactory(new PropertyValueFactory<>("plan"));

        //to get the selected item from tableview//
        table.setRowFactory(tv -> {
            TableRow<TravelGroup> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    TravelGroup clickedRow = table.getItems().get(myIndex);
                    try {

                        Stage popupwindow = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/TravelMembersPopUp.fxml"));
//                        TravelMembersPopUpController.setLog(clickedRow);
                        Parent root = loader.load();
                        TravelMembersPopUpController c = loader.getController();
//                        c.setLog(clickedRow);
                        Scene scene = new Scene(root);
                        popupwindow.setScene(scene);
                        popupwindow.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(TravelMembersPopUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });

    
        
 
           home.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    home.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/Dashboard.fxml")));
                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupAfficheController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    back.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelGroupListview.fxml")));
                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelGroupAfficheController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
            
          
 
        
        
    }
        
  
            @FXML
    private void handleButtonAction(MouseEvent event) {
        
        SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
            
            
            // TODO Auto-generated method stub
            JFrame frame = new JFrame("Live chat");

            // 2. Optional: What happens when the frame closes?
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

       
              new GUIServer().setVisible(true);
           new GUIClient("localhost", 1000).setVisible(true);
            // 4. Size the frame.
            frame.pack();

            // 5. Show it.
            frame.setVisible(true);
        }
    }); 
    
    }
}


