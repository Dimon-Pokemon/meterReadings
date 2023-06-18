package main;

import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MeteringDevice;
import model.ReadingLog;
import dataBaseTool.DAO;
import model.Street;
import model.TypeMeteringDevice;

import java.io.IOException;

public class MainApp extends Application{

    private Stage primaryStage;
    private Stage catalogStreetStage;
    private Stage addNewStreetStage;
    private Stage addNewMeteringDeviceStage;
    private Stage catalogTypeMeteringDeviceStage;
    private Stage addNewTypeMeteringDeviceOrEditItStage;

    private AnchorPane rootLayout;
    private AnchorPane catalogStreetLayout;
    private AnchorPane addNewStreetLayout;
    private AnchorPane addNewMeteringDeviceLayout;
    private AnchorPane catalogTypeMeteringDeviceLayout;
    private AnchorPane addNewTypeMeteringDeviceOrEditItLayout;

    private ObservableList<ReadingLog> readingsLog = FXCollections.observableArrayList();
    private ObservableList<Street> streets = FXCollections.observableArrayList();
    private ObservableList<String> regions = FXCollections.observableArrayList();
    private ObservableList<String> cities = FXCollections.observableArrayList();
    private ObservableList<TypeMeteringDevice> typesMeteringDevice = FXCollections.observableArrayList();
    private ObservableList<MeteringDevice> meteringDevices = FXCollections.observableArrayList();
    private ObservableList<String> facilities = FXCollections.observableArrayList();

    private DAO dao;

    public ObservableList<ReadingLog> getReadingsLog(){
        return readingsLog;
    }

    public ObservableList<Street> getStreets(){return streets;}

    public ObservableList<String> getFacilities(){return facilities;}

    public ObservableList<MeteringDevice> getMeteringDevices(){return meteringDevices;}

    public ObservableList<String> getRegions(){return regions;}

    public ObservableList<String> getCities(){return cities;}

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

            primaryStage = new Stage();
            EnteringReadingsController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDao(dao);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, отображающий форму добавления нового ИПУ.
     */
    public void showAddNewMeteringDevice(){
        try {
            FXMLLoader loader = loadResource("addNewMeteringDevice.fxml");
            addNewMeteringDeviceLayout = (AnchorPane) loader.load();

            addNewMeteringDeviceStage = new Stage();
            AddNewMeteringDeviceController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDao(dao);

            Scene scene = new Scene(addNewMeteringDeviceLayout);
            addNewMeteringDeviceStage.setScene(scene);
            addNewMeteringDeviceStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, отображающий форму справочника типов ИПУ.
     */
    public void showCatalogTypeMeteringDevice(){
        try {
            FXMLLoader loader = loadResource("catalogTypeMeteringDevice.fxml");
            catalogTypeMeteringDeviceLayout = (AnchorPane) loader.load();

            catalogTypeMeteringDeviceStage = new Stage();
            CatalogTypeMeteringDeviceController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDao(dao);

            Scene scene = new Scene(catalogTypeMeteringDeviceLayout);
            catalogTypeMeteringDeviceStage.setScene(scene);
            catalogTypeMeteringDeviceStage.setResizable(false);
            catalogTypeMeteringDeviceStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, отображающий форму справочника улиц.
     */
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
            catalogStreetStage.setResizable(false);
            catalogStreetStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Метод для отображения формы ввода или редактирования улицы (одной записи из справочника улиц).
     * @param mode режим формы - null для добавления улицы и UPDATE для редактирования.
     * @param selectedStreet выбранная для редактирования улица.
     */
    public void showAddNewStreetOrEditIt(String mode, Street selectedStreet){
        try {
            FXMLLoader loader = loadResource("addNewStreetOrEditIt.fxml");
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

    /**
     * Перегруженный метод для отображения формы ввода или редактирования улицы (одной записи из справочника улиц).
     */
    public void showAddNewStreetOrEditIt(){
        showAddNewStreetOrEditIt("ADD", null);
    }

    public void showAddNewTypeMeteringDeviceOrEditIt(String mode, TypeMeteringDevice typeMeteringDevice){
        try {
            FXMLLoader loader = loadResource("addNewTypeMeteringDeviceOrEditIt.fxml");
            addNewTypeMeteringDeviceOrEditItLayout = (AnchorPane) loader.load();

            AddNewTypeMeteringDeviceOrEditItController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDao(this.dao);

            if (mode.equals("UPDATE")){
                controller.setDataForUIElementsForEditTypeMeteringDevice(typeMeteringDevice);
            }

            addNewTypeMeteringDeviceOrEditItStage = new Stage();

            Scene scene = new Scene(addNewTypeMeteringDeviceOrEditItLayout);
            addNewTypeMeteringDeviceOrEditItStage.setScene(scene);
            addNewTypeMeteringDeviceOrEditItStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Конструктор основного класса.
     * Создает объект класса DAO, загружает все улицы, типы приборов учета
     * и улсуги из БД.
     */
    public MainApp(){
        this.dao = new DAO();
        this.streets.addAll(dao.getStreets());
        this.regions.addAll(dao.getRegionOrCity("region"));
        this.cities.addAll(dao.getRegionOrCity("city"));
        this.typesMeteringDevice.addAll(dao.getTypesMeteringDevice());
        this.facilities.addAll(dao.getFacilities());
    }

    public DAO getDao(){
        return dao;
    }

    /**
     * Метод закрытия формы добавления новой улицы.
     */
    public void closeWindowAddNewStreetStage(){
        this.addNewStreetStage.close();
    }

    public static void main(String args[]){
        MainApp mainApp = new MainApp();
        launch(args);
    }

    public ObservableList<TypeMeteringDevice> getTypesMeteringDevice(){
        return this.typesMeteringDevice;
    }
}
