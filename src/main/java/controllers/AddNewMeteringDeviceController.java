package controllers;

import main.DAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.MainApp;
import model.MeteringDevice;
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
    private ObservableList<MeteringDevice> mainMeteringDevice;
    private Long inputSerialNumber;
    private Long inputStreetId;
    private Long inputTypeMeteringDeviceId;

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
        if(getInputData()) {
            this.dao.addNewMeteringDevice(
                    inputSerialNumber,
                    inputStreetId,
                    inputTypeMeteringDeviceId
            );
            Dialog.successfulInfoWindow("Успех!", "Прибор учета с серийным номером %d успешно добавлен на улицу %s".formatted(inputSerialNumber, this.streets.getSelectionModel().getSelectedItem().getStreetName()));
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.streets.setItems(mainApp.getStreets());
        this.typesMeteringDevice.setItems(mainApp.getTypesMeteringDevice());
        this.mainMeteringDevice = mainApp.getMeteringDevices();
    }

    private boolean getInputData(){
        // Проверка выбранной улицы
        Street selectedStreet = this.streets.getSelectionModel().getSelectedItem();
        if (selectedStreet == null){
            Dialog.errorWindow("Внимание!", "Не выбрана улица!");
            return false;
        }
        inputStreetId = selectedStreet.getId();
        // Проверка выбранного серийного номера
        TypeMeteringDevice selectedTypeMeteringDevice = this.typesMeteringDevice.getSelectionModel().getSelectedItem();
        if (selectedTypeMeteringDevice == null){
            Dialog.errorWindow("Внимание!", "Не выбран тип ИПУ!");
            return false;
        }
        inputTypeMeteringDeviceId = selectedTypeMeteringDevice.getId();
        // Проверка введенного серийного номера
        String stringMeteringDevice = this.meteringDeviceSerialNumber.getText();
        if (!CheckInput.serialNumberCheck(stringMeteringDevice))
            return false;
        inputSerialNumber = Long.parseLong(stringMeteringDevice);
        return true;
    }

    public void setDao(DAO dao){this.dao = dao;}
}
