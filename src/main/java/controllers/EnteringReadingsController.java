package controllers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.MainApp;
import dataBaseTool.DAO;
import model.MeteringDevice;
import model.ReadingLog;
import model.Street;

import java.util.Date;

public class EnteringReadingsController {
    /**
     * ComboBox с текстовым полем и кнопкой
     */
    @FXML
    private ComboBox<Street> streets;
    @FXML
    private ComboBox<MeteringDevice> meteringDevice;
    @FXML
    private TextField newReading;
    @FXML
    private Button button;

    /**
     * Таблица
     */
    @FXML
    private TableView<ReadingLog> catalogReading;
    @FXML
    private TableColumn<ReadingLog, Date> date;
    @FXML
    private TableColumn<ReadingLog, String> serialNumberMeteringDevice;
    @FXML
    private TableColumn<ReadingLog, String> reading;
    @FXML
    private TableColumn<ReadingLog, String> street;
    @FXML
    private TableColumn<ReadingLog, String> capacity;


    private MainApp mainApp;
    private DAO dao;
    private Street selectedStreet;
    private MeteringDevice selectedMeteringDevice;

    public EnteringReadingsController(){
    }

    @FXML
    private void initialize(){
        meteringDevice.setDisable(true);
        button.setDisable(true);
        streets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actionStreetsComboBox();
            }
        });

        meteringDevice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actionMeteringDeviceComboBox();
            }
        });
    }

    private void actionStreetsComboBox(){
        selectedStreet = streets.getSelectionModel().getSelectedItem();
        meteringDevice.setDisable(false);
        meteringDevice.setItems(FXCollections.observableArrayList(dao.getMeteringDevices(selectedStreet)));
    }

    private void actionMeteringDeviceComboBox(){
        selectedMeteringDevice = meteringDevice.getSelectionModel().getSelectedItem();
        button.setDisable(false);
    }

    @FXML
    private void editCatalogStreet(){
        mainApp.showCatalogStreet();
    }

    @FXML
    private void addNewMeteringDevice(){ mainApp.showAddNewMeteringDevice(); }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

//        catalogReading.setItems(mainApp.getReadingsLog());
        streets.setItems(mainApp.getStreets());
    }

    public void setDao(DAO dao){this.dao = dao;}
}
