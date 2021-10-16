/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Event;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author ASUS
 */
public class EventListViewCell extends ListCell<Event> {
     @FXML
    private Label nom;

    @FXML
    private Label type;
    @FXML
    private Label lieu;
    @FXML
    private Label descr;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
 
    @FXML
    private Pane pane;

   

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Event ev, boolean empty) {
        super.updateItem(ev, empty);

        if(empty || ev == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
           
            nom.setText(ev.getNom());
            type.setText(ev.getType());
            lieu.setText(ev.getLieu());
            dated.setText(ev.getDate_debut());
            datef.setText(ev.getDate_fin());
            descr.setText(ev.getDescr());
          
            

           
            setText(null);
            setGraphic(pane);
        }

    }
}
