package controllers;

import dataBaseTool.DAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.MainApp;
import model.Street;
import model.TypeMeteringDevice;

public class AddNewMeteringDeviceController {

    @FXML
    private ComboBox<Street> streets;
    @FXML
    private ComboBox<TypeMeteringDevice> typesMeteringDevice;
    @FXML
    private TextField meteringDeviceSerialNumber;
    private MainApp mainApp;
    private DAO dao;

    public void AddNewMeteringDeviceController(){
    }

    @FXML
    private void initialize(){
    }

    @FXML
    private void editCatalogTypeMeteringDevice(){
        this.mainApp.showCatalogTypeMeteringDevice();
    }
    @FXML
    private void add(){
        this.dao.addNewMeteringDevice(
                Long.parseLong(this.meteringDeviceSerialNumber.getText()),
                this.streets.getSelectionModel().getSelectedItem().getId(),
                this.typesMeteringDevice.getSelectionModel().getSelectedItem().getId()
        );
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.streets.setItems(mainApp.getStreets());
        this.typesMeteringDevice.setItems(mainApp.getTypesMeteringDevice());
    }

    public void setDao(DAO dao){this.dao = dao;}
}
