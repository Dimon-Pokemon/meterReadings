package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import main.MainApp;
import model.Street;
import DAO.DAO;


public class CatalogStreetController {
    @FXML
    private TableView<Street> tableStreet;

    @FXML
    private TableColumn<Street, Long> id;

    @FXML
    private TableColumn<Street, String> region;

    @FXML
    private TableColumn<Street, String>  city;

    @FXML
    private TableColumn<Street, String> street;

    private MainApp mainApp;

    private DAO dao;

    public CatalogStreetController(){
    }

    @FXML
    private void deleteStreet(){
        Street streetForDelete = tableStreet.getSelectionModel().selectedItemProperty().getValue();
        if (streetForDelete != null){
            Long streetId = streetForDelete.getId();
            dao.deleteStreet(streetId);
            int selectedIndex = tableStreet.getSelectionModel().getSelectedIndex();
            tableStreet.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void addNewStreet(){
        mainApp.showAddNewStreet();
    }
    @FXML
    private void updateStreet(){

    }

    @FXML
    private void initialize(){
        id.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        region.setCellValueFactory(cellData -> cellData.getValue().getRegionProperty());
        city.setCellValueFactory(cellData -> cellData.getValue().getCityProperty());
        street.setCellValueFactory(cellData -> cellData.getValue().getStreetNameProperty());
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        tableStreet.setItems(mainApp.getStreets());
    }

    public void setDao(DAO dao){
        this.dao = dao;
    }


}
