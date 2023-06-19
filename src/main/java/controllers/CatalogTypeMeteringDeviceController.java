package controllers;

import javafx.fxml.FXML;
//import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.MainApp;
import main.DAO;
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
        TypeMeteringDevice typeForDelete = tableTypeMeteringDevice.getSelectionModel().selectedItemProperty().getValue();
        if (typeForDelete != null){
            dao.deleteTypeMeteringDevice(typeForDelete.getId());
            tableTypeMeteringDevice.getItems().remove(tableTypeMeteringDevice.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    private void addType(){
        mainApp.showAddNewTypeMeteringDeviceOrEditIt("ADD", null);
    }

    @FXML
    private void editType(){
        TypeMeteringDevice selectedTypeMeteringDevice = tableTypeMeteringDevice.getSelectionModel().getSelectedItem();
        if (selectedTypeMeteringDevice != null)
            mainApp.showAddNewTypeMeteringDeviceOrEditIt("UPDATE", selectedTypeMeteringDevice);
        else
            Dialog.errorWindow("Внимание", "Вы не выбрали тип прибора учета из таблицы!");
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
