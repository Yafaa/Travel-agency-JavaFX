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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */



public class TravelBuddyStatsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private PieChart travelgroupStat;

    @FXML
    private PieChart travelbuddyStat;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        List<TravelGroup> travelgroups = new ArrayList<>();
        List<Travelbuddy> travelbuddies = new ArrayList<>();
        try {
            travelbuddies = TravelbuddyCrud.getAllTravelbuddy() ;
            travelgroups = TravelGroupCrud.getAllTravelGroup() ;
        } catch (SQLException ex) {
            Logger.getLogger(TravelGroupAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<TravelGroup> obsl = FXCollections.observableList(travelgroups);
        ObservableList<Travelbuddy> obs2 = FXCollections.observableList(travelbuddies);
        
        //intiliaze les deux liste de string
//        
//        obsl.stream().map(s -> s.getDestination()).forEach(x -> new travelgroupS);

        
       
        
    }    
    
}
