package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import main.MainApp;

public class EnteringReadingsController {
    @FXML
    private ComboBox<String> streets;
    @FXML
    private ComboBox<String> meteringDevice;
    @FXML
    private TextField newReading;
    @FXML
    private TableView<String> catalogReading;

    private MainApp mainApp;

    public EnteringReadingsController(){
    }

    @FXML
    private void initialize(){

    }

    @FXML
    private void editCatalogStreet(){
        mainApp.showCatalogStreet();
    }

    @FXML
    private void editMeteringDeviceController(){ mainApp.showCatalogMeteringDevice(); }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
