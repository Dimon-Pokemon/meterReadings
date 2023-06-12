package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.MainApp;
import DAO.DAO;

public class AddNewStreetOrUpdateItController {

    @FXML
    private ComboBox<String> region;

    @FXML
    private ComboBox<String> city;

    @FXML
    private TextField street;

    private MainApp mainApp;
    private DAO dao;

    public AddNewStreetOrUpdateItController(){
    }

    @FXML
    private void initialize(){
//        region.setCellFactory();
    }

    public void add(){
        dao.addNewStreet(
                region.getSelectionModel().getSelectedItem(),
                city.getSelectionModel().getSelectedItem(),
                street.getText()
        );
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;


        ObservableList<String> regions = FXCollections.observableArrayList();
        regions.addAll(mainApp.getStreets().stream().map(street -> {
            return street.getRegion();
        }).toList());
        this.region.setItems(regions);

        ObservableList<String> cities = FXCollections.observableArrayList();
        cities.addAll(mainApp.getStreets().stream().map(street -> {
            return street.getCity();
        }).toList());
        this.city.setItems(cities);


    }

    public void setDao(DAO dao){
        this.dao = dao;
    }
}
