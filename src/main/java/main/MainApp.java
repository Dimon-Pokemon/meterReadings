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
import model.MeteringDevice;
import model.ReadingLog;
import DAO.DAO;
import model.Street;
import model.TypeMeteringDevice;

import java.io.IOException;

public class MainApp extends Application{

    private Stage primaryStage;
    private Stage catalogStreetStage;
    private Stage addNewStreetStage;
    private Stage catalogMeteringDeviceStage;
    private Stage addNewMeteringDeviceStage;
    private Stage catalogTypeMeteringDeviceStage;
    private AnchorPane rootLayout;
    private AnchorPane catalogStreetLayout;
    private AnchorPane addNewStreetLayout;

    private AnchorPane catalogMeteringDeviceLayout;
    private AnchorPane addNewMeteringDeviceLayout;
    private AnchorPane catalogTypeMeteringDeviceLayout;

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

    /**
     * Метод, отображающий форму добавления нового ИПУ.
     */
    public void showAddNewMeteringDevice(){
        try {
            FXMLLoader loader = loadResource("addNewMeteringDevice.fxml");
            addNewStreetLayout = (AnchorPane) loader.load();

            addNewMeteringDeviceStage = new Stage();

            Scene scene = new Scene(addNewMeteringDeviceLayout);
            addNewMeteringDeviceStage.setScene(scene);
            addNewMeteringDeviceStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, отображающий форму справочника ИПУ.
     */
    public void showCatalogMeteringDevice(){
        try{
            FXMLLoader loader = loadResource("catalogMeteringDevice.fxml");
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

            Scene scene = new Scene(catalogTypeMeteringDeviceLayout);
            catalogTypeMeteringDeviceStage.setScene(scene);
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
            catalogStreetStage.show();
            catalogStreetStage.setResizable(false);
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
        showAddNewStreetOrEditIt(null, null);
    }

    /**
     * Конструктор основного класса.
     * Создает объект класса {DAO} и загружает все улицы с БД.
     */
    public MainApp(){
        this.dao = new DAO();
        this.streets.addAll(dao.getStreets());
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
}
