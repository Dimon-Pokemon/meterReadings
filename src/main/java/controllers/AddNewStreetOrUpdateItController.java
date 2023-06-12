package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.MainApp;
import DAO.DAO;
import model.Street;

import java.util.Optional;

public class AddNewStreetOrUpdateItController {

    @FXML
    private ComboBox<String> region;

    @FXML
    private ComboBox<String> city;

    @FXML
    private TextField street;

    private MainApp mainApp;
    private DAO dao;

    private ObservableList<Street> mainStreets;

    public AddNewStreetOrUpdateItController(){
    }

    @FXML
    private void initialize(){
    }

    public void add(){
        Boolean flag = true;
        for (int i = 0; i<mainStreets.size(); i++){
            if (mainStreets.get(i).toString().equals("%s, %s, %s"
                    .formatted(
                            region.getSelectionModel().getSelectedItem(),
                            city.getSelectionModel().getSelectedItem(),
                            this.street.getText()))) {
                flag = false;
                break;
            }
        }
        if (flag){
            dao.addNewStreet(
                    region.getSelectionModel().getSelectedItem(),
                    city.getSelectionModel().getSelectedItem(),
                    street.getText()
            );
            mainStreets.removeAll(mainStreets);
            mainStreets.addAll(dao.getStreets());

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Успех!");
            info.setHeaderText("Улица успешно добавлена в справочнк улиц!");
            info.setContentText(null);
            info.showAndWait();

            street.clear();
        } else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Ошибка! Дубликат улицы.");
            error.setHeaderText("Нельзя добавить улицу, так как запись с такой улицей уже существует в справочнике улиц!");
            error.setContentText(null);

            error.show();
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.mainStreets = mainApp.getStreets();


        ObservableList<String> regions = FXCollections.observableArrayList();
        regions.addAll(mainApp.getStreets().stream().map(street -> {
            return street.getRegion();
        }).distinct().toList());
        this.region.setItems(regions);

        ObservableList<String> cities = FXCollections.observableArrayList();
        cities.addAll(mainApp.getStreets().stream().map(street -> {
            return street.getCity();
        }).distinct().toList());
        this.city.setItems(cities);
    }

    public void setDao(DAO dao){
        this.dao = dao;
    }
}
