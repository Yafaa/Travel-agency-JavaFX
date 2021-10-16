/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.TravelGroup;
import Entities.Travelbuddy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelGroupListeCell extends ListCell<TravelGroup> {
    @FXML
    private Pane pane;
    @FXML
    private Label title;
    @FXML
    private Label destination;
    @FXML
    private Label date_debut;
    @FXML
    private Label date_fin;
    @FXML
    private Label plan;
    @FXML
    private ImageView image;
private FXMLLoader mLLoader ;
    
    /**
     * Initializes the controller class.
     */
    protected void updateItem(TravelGroup t, boolean empty) {
        super.updateItem(t, empty);

        if(empty || t == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("TravelGroupListeCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                title.setText(t.getTitle());
                destination.setText(t.getDestination());
                date_debut.setText( t.getDate_debut().toString() );
                date_fin.setText( t.getDate_debut().toString() );
                plan.setText(t.getPlan());
//                Image img = new Image(t.getPicture) ;
//                
//                image.setImage(img);
            }

           


            setText(null);
            setGraphic(pane);
        }    
    }
    
}
