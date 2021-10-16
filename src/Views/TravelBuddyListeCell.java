/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Travelbuddy;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelBuddyListeCell extends ListCell<Travelbuddy>{
    
    @FXML
    private Label date_debut;

    @FXML
    private Label destination;

    @FXML
    private Label description;

    @FXML
    private Label date_fin;

    @FXML
    private Pane pane;
    
    private FXMLLoader mLLoader ;
    
    /**
     * Initializes the controller class.
     */
    
        // TODO
     @Override
    protected void updateItem(Travelbuddy t, boolean empty) {
        super.updateItem(t, empty);

        if(empty || t == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("TravelBuddyListeCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                description.setText(t.getDescription());
                destination.setText(t.getDestination());
                date_debut.setText( t.getDate_debut().toString() );
                date_fin.setText( t.getDate_debut().toString() );
                
                

            }

           


            setText(null);
            setGraphic(pane);
        }    
    }
}
