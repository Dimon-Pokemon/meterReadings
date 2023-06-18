package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private TextField accuracy;
    @FXML
    private Button button;

    private MainApp mainApp;
    private DAO dao;

    private String inputTitleType;
    private String inputFacility;
    private Integer inputCapacity;
    private Integer inputAccuracy;

    private ObservableList<TypeMeteringDevice> mainTypeMeteringDevice;

    public AddNewTypeMeteringDeviceOrEditItController(){
    }

    private Boolean observableListTypeHasNotDuplicateItem(String inputTitleType){
        for (int i=0; i< mainTypeMeteringDevice.size(); i++){
            if (mainTypeMeteringDevice.get(i).equals(inputTitleType)){
                return false;
            }
        }
        return true;
    }

    /**
     * Метод для получения введенных строк с текстовых полей ввода и выбранного значения из выпадающего списка
     */
    private void getInput(){
        inputTitleType = this.titleType.getText(); // Получение введенного наименования типа ИПУ через текстовое поле ввода
        inputFacility = this.facility.getSelectionModel().getSelectedItem(); // Получение выбранной через выпадающий список услуги
        inputCapacity = Integer.parseInt(this.capacity.getText()); // Получение разрядности, указанную через текстовое поле ввода
        inputAccuracy = Integer.parseInt(this.accuracy.getText()); // Получение точности ИПУ, т.е. кол-во знаков после запятой
    }

    @FXML
    private void add(){
        getInput();
        if (observableListTypeHasNotDuplicateItem(inputTitleType)){
            dao.addNewTypeMeteringDevice(
                    inputTitleType,
                    inputFacility,
                    inputCapacity,
                    inputAccuracy
            );
            Dialog.successfulInfoWindow("Успех!", "Новый тип прибора учета успешно добавлен в спарвочник ИПУ.");

            this.mainTypeMeteringDevice.removeAll(this.mainTypeMeteringDevice);
            this.mainTypeMeteringDevice.addAll(this.dao.getTypesMeteringDevice());

        } else {
            Dialog.errorWindow("Ошибка!", "Тип прибора уже существует!");
        }
    }

    @FXML
    private void update(){
        getInput();
        if (observableListTypeHasNotDuplicateItem(inputTitleType)){
            dao.updateTypeMeteringDevice(
                    inputTitleType,
                    inputFacility,
                    inputCapacity,
                    inputAccuracy
            );
            Dialog.successfulInfoWindow("Успех!", "Тип прибора был изменен.");

            this.mainTypeMeteringDevice.removeAll(this.mainTypeMeteringDevice);
            this.mainTypeMeteringDevice.addAll(this.dao.getTypesMeteringDevice());

        } else {
            Dialog.errorWindow("Ошибка!", "Невозможно изменить тип прибора. Тип прибора уже существует!");
        }
    }

    public void setDataForUIElementsForEditTypeMeteringDevice(TypeMeteringDevice typeMeteringDeviceForEdit){
        titleType.setText(typeMeteringDeviceForEdit.getTitleType());
        facility.getSelectionModel().select(typeMeteringDeviceForEdit.getFacility());
        capacity.setText(typeMeteringDeviceForEdit.getCapacity().toString());
        accuracy.setText(typeMeteringDeviceForEdit.getAccuracy().toString());

        this.button.setText("Изменить");

        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                update();}
        });
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
