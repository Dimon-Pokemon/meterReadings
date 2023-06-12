package main;

import controllers.AddNewStreetOrUpdateItController;
import controllers.CatalogStreetController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ReadingLog;
import DAO.DAO;
import model.Street;

import java.io.IOException;

public class MainApp extends Application{

    private Stage primaryStage;
    private Stage catalogStreetStage;
    private Stage addNewStreetStage;
    private AnchorPane rootLayout;
    private AnchorPane catalogStreetLayout;
    private AnchorPane addNewStreetLayout;

    private ObservableList<ReadingLog> readingsLog = FXCollections.observableArrayList();
    private ObservableList<Street> streets = FXCollections.observableArrayList();
    private ObservableList<String> facilities = FXCollections.observableArrayList();

    private DAO dao;

    public ObservableList<ReadingLog> getReadingsLog(){
        return readingsLog;
    }

    public ObservableList<Street> getStreets(){return streets;}

    public ObservableList<String> getFacilities(){return facilities;}

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        setRootLayout();
    }

    private FXMLLoader loadResource(String url){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(url));

        return loader;
    }

    private void setRootLayout(){
        try{
            FXMLLoader loader = loadResource("enteringReadings.fxml");
            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showCatalogStreet(){
        try {
            FXMLLoader loader = loadResource("catalogStreet.fxml");
            catalogStreetLayout = (AnchorPane) loader.load();

            CatalogStreetController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDao(dao);

            catalogStreetStage = new Stage();

            Scene scene = new Scene(catalogStreetLayout);
            catalogStreetStage.setScene(scene);
            catalogStreetStage.show();
            catalogStreetStage.setResizable(false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showAddNewStreetOrUpdateIt(){
        showAddNewStreetOrUpdateIt("new", null);
    }

    public void showAddNewStreetOrUpdateIt(String mode, Street selectedStreet){
        try {
            FXMLLoader loader = loadResource("addNewStreet.fxml");
            addNewStreetLayout = (AnchorPane) loader.load();

            AddNewStreetOrUpdateItController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDao(dao);
            if (mode.equals("UPDATE")) {
                controller.setValueFromSelectedStreetForUpdate(selectedStreet);
            }

            addNewStreetStage = new Stage();

            Scene scene = new Scene(addNewStreetLayout);
            addNewStreetStage.setScene(scene);
            addNewStreetStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public MainApp(){
        this.dao = new DAO();
        this.streets.addAll(dao.getStreets());
    }

    public DAO getDao(){
        return dao;
    }

    public void closeWindowAddNewStreetStage(){
        this.addNewStreetStage.close();
    }

    public static void main(String args[]){
        MainApp mainApp = new MainApp();
        launch(args);
    }
}
