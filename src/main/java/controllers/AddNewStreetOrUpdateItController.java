package controllers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.MainApp;
import main.DAO;
import model.Street;

public class AddNewStreetOrUpdateItController {

    @FXML
    private ComboBox<String> region;

    @FXML
    private ComboBox<String> city;

    @FXML
    private TextField street;

    @FXML
    private Button button;

    private MainApp mainApp;
    private DAO dao;
    private ObservableList<Street> mainStreets;
    private Street selectedStreetForUpdate;
    private String selectedRegion;
    private String selectedCity;
    private String inputNameStreet;

    public AddNewStreetOrUpdateItController(){
    }

    @FXML
    private void initialize(){
    }

    private boolean observableListStreetHasNotDuplicateItem(ObservableList<Street> observableListWithStreet, Street street){
        for (int i = 0; i<observableListWithStreet.size(); i++){
            if(observableListWithStreet.get(i).toString().equals("%s, %s, %s"
                    .formatted(
                            street.getRegion(),
                            street.getCity(),
                            street.getStreetName()))) {
                return false;
            }
        }
        return true;
    }
    private boolean observableListStreetHasNotDuplicateItem(ObservableList<Street> observableListWithStreet){
        for (int i = 0; i<observableListWithStreet.size(); i++){
            if (observableListWithStreet.get(i).toString().equals("%s, %s, %s"
                    .formatted(
                            this.region.getSelectionModel().getSelectedItem(),
                            this.city.getSelectionModel().getSelectedItem(),
                            this.street.getText()))) {
                return false;
            }
        }
        return true;
    }

    private boolean getInput(){
        this.selectedRegion = this.region.getSelectionModel().getSelectedItem();
        if (selectedRegion == null){
            Dialog.errorWindow("Ошибка. Регион не выбран!", "Выберите регион.");
            return false;
        }

        this.selectedCity = this.city.getSelectionModel().getSelectedItem();
        if (selectedCity == null){
            Dialog.errorWindow("Ошибка. Город не выбран!", "Выберите город.");
            return false;
        }

        this.inputNameStreet = this.street.getText();
        if (inputNameStreet == ""){
            Dialog.errorWindow("Ошибка. Наименование улицы не введено!", "Введите наименование улицы.");
            return false;
        }
        return true;
    }

    @FXML
    private void add(){
        if (observableListStreetHasNotDuplicateItem(this.mainStreets)){
            if (getInput()) {
                this.dao.addNewStreet(
                        selectedRegion,
                        selectedCity,
                        inputNameStreet
                );
                this.mainStreets.removeAll(this.mainStreets);
                this.mainStreets.addAll(this.dao.getStreets());

                Dialog.successfulInfoWindow("Успех!", "Улица успешно добавлена в справочнк улиц!");

                street.clear();
            }
        } else{
            Dialog.errorWindow(
                    "Ошибка! Дубликат улицы.",
                    "Нельзя добавить улицу, так как запись с такой улицей уже существует в справочнике улиц!"
            );
        }
    }

    @FXML
    private void update(){
        if (observableListStreetHasNotDuplicateItem(this.mainStreets)){
            if (getInput()) {
                dao.updateStreet(
                        selectedStreetForUpdate.getId(),
                        selectedRegion,
                        selectedCity,
                        inputNameStreet
                );

                this.mainStreets.removeAll(this.mainStreets);
                this.mainStreets.addAll(this.dao.getStreets());

                Dialog.successfulInfoWindow("Успех!", "Запись успешно обнавлена в справочнике");

                this.mainApp.closeWindowAddNewStreetStage();
            }
        } else{
            Dialog.errorWindow(
                    "Ошибка!",
                    "Не удалось обновить запись в справочнике улиц - данная улица уже существует в справочнике."
            );
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.mainStreets = mainApp.getStreets();
        this.region.setItems(mainApp.getRegions());
        this.city.setItems(mainApp.getCities());
    }

    public void setDao(DAO dao){
        this.dao = dao;
    }

    public void setValueFromSelectedStreetForUpdate(Street street){
        this.selectedStreetForUpdate = street;
        this.region.getSelectionModel().select(street.getRegion());
        this.city.getSelectionModel().select(street.getCity());
        this.street.setText(street.getStreetName());

        this.button.setText("Изменить");
        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                update();
            }
        });
    }
}
