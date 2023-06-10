package main;

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
import java.util.Collection;

public class EnteringReadings extends Application{

    private Stage primaryStage;
    private Stage catalogStreetStage;
    private AnchorPane rootLayout;
    private AnchorPane catalogStreetLayout;

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
        showCatalogStreet();
    }

    private void setRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EnteringReadings.class.getResource("enteringReadings.fxml"));
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EnteringReadings.class.getResource("catalogStreet.fxml"));
            catalogStreetLayout = (AnchorPane) loader.load();

            CatalogStreetController controller = loader.getController();
            controller.setMainApp(this);

            this.catalogStreetStage = new Stage();

            Scene scene = new Scene(catalogStreetLayout);
            catalogStreetStage.setScene(scene);
            catalogStreetStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public EnteringReadings(){
        DAO dao = new DAO();
        this.streets.addAll(dao.getStreets());
    }

    public static void main(String args[]){

//        try{
//            DAO dao = new DAO();
//            ArrayList<Street> streets = dao.getStreets();
//            for(int i = 0; i<streets.size(); i++){
//                System.out.println(streets.get(i).toString());
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        EnteringReadings enteringReadings = new EnteringReadings();
        launch(args);
    }
}
