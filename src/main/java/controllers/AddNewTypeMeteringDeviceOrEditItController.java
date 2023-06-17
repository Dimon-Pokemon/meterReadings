package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.MainApp;
import dataBaseTool.DAO;
import model.TypeMeteringDevice;
import dialog.Dialog;

public class AddNewTypeMeteringDeviceOrEditItController {

    @FXML
    private TextField titleType;
    @FXML
    private ComboBox<String> facility;
    @FXML
    private TextField capacity;

    private MainApp mainApp;
    private DAO dao;

    private String inputTitleType;
    private String inputFacility;
    private Integer inputCapacity;

    private ObservableList<TypeMeteringDevice> mainTypeMeteringDevice;

    public AddNewTypeMeteringDeviceOrEditItController(){
    }

    private Boolean observableListTypeHasNotDuplicateItem(String inputDataConvertedToString){
        for (int i=0; i< mainTypeMeteringDevice.size(); i++){
            if (mainTypeMeteringDevice.get(i).equals(inputDataConvertedToString)){
                return false;
            }
        }
        return true;
    }

    private void getInput(){
        inputTitleType = this.titleType.getText();
        inputFacility = this.facility.getSelectionModel().getSelectedItem();
        inputCapacity = Integer.parseInt(this.capacity.getText());
    }

    private String convertInputDataToString(){
        return "%s (Facility: %s; Capacity: %d)".formatted(inputTitleType, inputFacility, inputCapacity);
    }

    @FXML
    private void addNewType(){
        getInput();
        if (observableListTypeHasNotDuplicateItem(inputTitleType)){
            dao.addNewTypeMeteringDevice(
                    inputTitleType,
                    inputFacility,
                    inputCapacity
            );
            Dialog.successfulInfoWindow("Успех!", "Новый тип прибора учета успешно добавлен в спарвочник ИПУ.");
        } else {
            Dialog.errorWindow("Ошибка!", "Тип прибора уже существует!");
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.mainTypeMeteringDevice = mainApp.getTypesMeteringDevice();

        this.facility.setItems(mainApp.getFacilities());
    }

    public void setDao(DAO dao){
        this.dao = dao;
    }
}
