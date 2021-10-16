 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Travelbuddy;
import Services.TravelGroupCrud;
import Services.TravelbuddyCrud;
import Utils.MyDBcon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TravelBuddyListeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane myPane;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableColumn<Travelbuddy, String> description;
    @FXML
    private TableColumn<Travelbuddy, String> destination;
    @FXML
    private TableColumn<Travelbuddy, Date> date_debut;
    @FXML
    private TableColumn<Travelbuddy, Date> date_fin;
    @FXML
    private TableView<Travelbuddy> table;
    @FXML
    private JFXButton del;

    @FXML
    private ImageView home;

    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    //methode de refraichir la page//
    private void refresh() throws SQLException {
        display();
    }

    void alert(String text) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(text);

        alert.showAndWait();
    }

    // methode d'affichage //
    public void display() throws SQLException {
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        ObservableList<Travelbuddy> OL = FXCollections.observableList(TravelbuddyCrud.getAllTravelbuddy());
        table.setItems(OL);
    }

    void update() throws SQLException, IOException {
        description.setCellFactory(TextFieldTableCell.<Travelbuddy>forTableColumn());
        destination.setCellFactory(TextFieldTableCell.<Travelbuddy>forTableColumn());

        description.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Travelbuddy, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Travelbuddy, String> t) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Fenêtre de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de la modification ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (t.getNewValue().matches("")) {
                        alert("Result format not valid");
                        try {
                            refresh();
                        } catch (SQLException ex) {
                            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
//                        ((Travelbuddy) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDescription(t.getNewValue());
                        try {
                            TravelbuddyCrud.update("Description :", t.getNewValue(), ((Travelbuddy) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    try {
                        refresh();
                    } catch (SQLException ex) {
                        Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    alert.close();
                }

            }
        });
        destination.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Travelbuddy, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Travelbuddy, String> t) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Fenêtre de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de la modification ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (t.getNewValue().matches("")) {
                        alert("Le champ est vide !");
                        try {
                            refresh();
                        } catch (SQLException ex) {
                            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
//                        ((Travelbuddy) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDestination(t.getNewValue());
                        try {
                            TravelbuddyCrud.update("destination", t.getNewValue(), ((Travelbuddy) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    try {
                        refresh();
                    } catch (SQLException ex) {
                        Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    alert.close();
                }

            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
        ArrayList<Travelbuddy> travelbuddys = new ArrayList<>();
        try {
            travelbuddys = (ArrayList<Travelbuddy>) TravelbuddyCrud.getAllTravelbuddy();
        } catch (SQLException ex) {
            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Travelbuddy> obsl = FXCollections.observableArrayList(travelbuddys);
        table.setItems(obsl);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        try {
            List<Travelbuddy> t1 = TravelbuddyCrud.getAllTravelbuddy();
        } catch (SQLException ex) {
            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FilteredList<Travelbuddy> filterData = new FilteredList<Travelbuddy>(obsl, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super Travelbuddy>) Travelbuddy -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Travelbuddy.getDescription().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (Travelbuddy.getDestination().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (Travelbuddy.getDate_debut().toString().contains(newValue)) {
                        return true;
                    } else if (Travelbuddy.getDate_fin().toString().contains(newValue)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Travelbuddy> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);

        });
        try {
            display();
            update();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory(tv -> {
            TableRow<Travelbuddy> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    Travelbuddy clickedRow = table.getItems().get(myIndex);

                    try {
                        printRow(clickedRow);
                    } catch (SQLException ex) {
                        Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    back.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/TravelBuddyListview.fxml")));
                    event.consume();

                } catch (IOException ex) {
                    Logger.getLogger(TravelBuddyListviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      
        
        
       
    }

    @FXML
    private void InfotravelBuddy(MouseEvent event) throws IOException {
        Pane myPane1 = FXMLLoader.load(getClass().getResource("/views/TravelBuddyEntry.fxml"));
        myPane.getChildren().setAll(myPane1);
    }

    private void printRow(Travelbuddy item) throws SQLException {

        Connection cnx;
        cnx = MyDBcon.getInstance().getCon();

        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                   
                    List<Integer> ids = TravelbuddyCrud.getIdTravelBuddyByIdUser1(item.getId_user()) ;
                for( int idd : ids) {
                    TravelGroupCrud.supprimerUnMemberFromAll(idd);
                }
                  
                   
                    TravelbuddyCrud.supprimerTravelBuddy(item);

//                    int selectedindex = table.getSelectionModel().getSelectedIndex();
//                    table.getItems().remove(selectedindex);
                    refresh();
                    Notifications.create()
                            .title("Amiticia")
                            .text("Travel buddy supprimé avec succés!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                } catch (SQLException ex1) {
                    Logger.getLogger(TravelBuddyListeController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        });
    }

}
