package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.MainApp;
import DAO.DAO;
import model.TypeMeteringDevice;

public class CatalogTypeMeteringDeviceController {

    @FXML
    private TableView<TypeMeteringDevice> tableTypeMeteringDevice;

    @FXML
    private TableColumn<TypeMeteringDevice, String> titleType;

    @FXML
    private TableColumn<TypeMeteringDevice, String> facility;

    @FXML
    private TableColumn<TypeMeteringDevice, Integer> capacity;

    private MainApp mainApp;
    private DAO dao;

    @FXML
    private void deleteType(){

    }

    @FXML
    private void addType(){
    }

    @FXML
    private void editType(){
    }

    @FXML
    public void initialize(){
        titleType.setCellValueFactory(cellData -> cellData.getValue().getTitleTypeProperty());
        facility.setCellValueFactory(cellData -> cellData.getValue().getFacilityProperty());
        capacity.setCellValueFactory(cellData -> cellData.getValue().getCapacityProperty().asObject());
    }

    public void CatalogTypeMeteringDeviceController(){
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        this.tableTypeMeteringDevice.setItems(mainApp.getTypesMeteringDevice());
    }

    public void setDao(DAO dao){
        this.dao = dao;
    }
}
