package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import main.EnteringReadings;
import model.Street;


public class CatalogStreetController {
    @FXML
    private TableView<Street> tableStreet;

    @FXML
    private TableColumn<Street, Long> id;

    @FXML
    private TableColumn<Street, String> region;

    @FXML
    private TableColumn<Street, String>  city;

    @FXML
    private TableColumn<Street, String> street;

    @FXML
    private Label regionLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label streetLabel;

    private EnteringReadings mainApp;

    public CatalogStreetController(){
    }

    @FXML
    private void initialize(){
        id.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        region.setCellValueFactory(cellData -> cellData.getValue().getRegionProperty());
        city.setCellValueFactory(cellData -> cellData.getValue().getCityProperty());
        street.setCellValueFactory(cellData -> cellData.getValue().getStreetNameProperty());
    }

    public void setMainApp(EnteringReadings mainApp){
        this.mainApp = mainApp;

        tableStreet.setItems(mainApp.getStreets());
    }


}
