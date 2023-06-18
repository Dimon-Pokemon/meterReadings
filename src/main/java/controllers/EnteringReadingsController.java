package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.MainApp;
import main.DAO;
import model.MeteringDevice;
import model.ReadingLog;
import model.Street;
import dialog.Dialog;

import java.util.Date;

public class EnteringReadingsController {
    /**
     * ComboBox с текстовым полем и кнопкой
     */
    @FXML
    private ComboBox<Street> streets;
    @FXML
    private ComboBox<MeteringDevice> meteringDevice;
    @FXML
    private TextField newReading;
    @FXML
    private Button button;

    /**
     * Таблица
     */
    @FXML
    private TableView<ReadingLog> catalogReading;
    @FXML
    private TableColumn<ReadingLog, Date> date;
    @FXML
    private TableColumn<ReadingLog, String> serialNumberMeteringDevice;
    @FXML
    private TableColumn<ReadingLog, String> titleMeteringDevice;
    @FXML
    private TableColumn<ReadingLog, Double> reading;
    @FXML
    private TableColumn<ReadingLog, String> street;
    @FXML
    private TableColumn<ReadingLog, Integer> capacity;


    private MainApp mainApp;
    private DAO dao;
    private Street selectedStreet;
    private MeteringDevice selectedMeteringDevice;
    private Double maxDiff = 250.0;
    private Double minDiff = 10.0;

    private ObservableList<ReadingLog> readingLogs = FXCollections.observableArrayList();

    public EnteringReadingsController(){
    }

    @FXML
    private void initialize(){
        meteringDevice.setDisable(true);
        button.setDisable(true);
        streets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actionForSelectedStreetsFromComboBox();
            }
        });

        meteringDevice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actionForMeteringDeviceFromComboBox();
            }
        });

        date.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        serialNumberMeteringDevice.setCellValueFactory(cellData -> cellData.getValue().getSerialNumberMeteringDeviceProperty());
        titleMeteringDevice.setCellValueFactory(cellData -> cellData.getValue().getTitleTypeMeteringDeviceProperty());
        reading.setCellValueFactory(cellData -> cellData.getValue().getReadingsProperty().asObject());
        street.setCellValueFactory(cellData -> cellData.getValue().getStreetProperty());
        capacity.setCellValueFactory(cellData -> cellData.getValue().getCapacityProperty().asObject());

    }

    /**
     * Метод определяет максимальное число, которое способен отобразить ИПУ с заданной разрядностью и точносью.
     * Т.е. если разрядность равна 5, а точость 1, то максимальное число - 9999,9.
     * @param capacity разрядность ИПУ, т.е. количество цифр, отображаемых на счетчике (ИПУ)
     * @param accuracy точность, т.е. количество цифр после запятой
     * @return максимально возможное число, которое может отобрзить счетчик (ИПУ)
     */
    private Double getMaxNumber(Integer capacity, Integer accuracy){
        StringBuilder result = new StringBuilder();
        Integer countDecimalBeforeComma = capacity - accuracy;
        for (int i = 0; i<capacity-accuracy; i++){
            result.append("9");
        }
        result.append(".");
        for (int i = accuracy+1; i<capacity; i++){
            result.append("9");
        }
        return Double.parseDouble(result.toString());
    }

    /**
     * Метод для добавления новой записи. Привязан к кнопке "Добавить новое показание".
     * Учитывается то, что новое показание не может быть меньше предыдущего.
     * Также учитывается тот факт, что показание может быть меньше предыдущего, если счетчик пересек свою границу
     * и начал считать заново.
     */
    @FXML
    private void addNewMetering(){
        // Новое показание в виде строки, в которой заменяется ',' на '.'
        String inputReading = newReading.getText().replace(',', '.');
        // Новое показание, преобразованное к типу Double
        Double inputReadingValue = Double.parseDouble(inputReading);
        // Максимальное значение счетчика
        Double maxValue = getMaxNumber(selectedMeteringDevice.getCapacity(), selectedMeteringDevice.getAccuracy());
        if (readingLogs.size()>0){// Есть ли предыдущие показания
            Double lastValue = readingLogs.get(0).getReadings(); // Получение предыдущего показания
            if (inputReadingValue<lastValue){
                /**
                 Разность между прошлым и новым показанием с предположением, что счетчик мог пересечь свое предельное значение
                 и потому новое показание меньше предыдущего
                 */
                Double diff = maxValue - lastValue + inputReadingValue;
                if (diff > maxDiff)
                    Dialog.errorWindow("Ошибка!", "Новое показание не может быть меньше предыдущего");
            }
            if (inputReadingValue-lastValue>maxDiff)
                Dialog.errorWindow("Ошибка!", "Новое показание не может отличаться от последнего более чем на %f ед.и.".formatted(maxDiff));
            else if (inputReadingValue-lastValue<minDiff) {
                Dialog.errorWindow("Ошибка!", "Новое показание должно отличаться от старого минимум на %f ед.и.".formatted(minDiff));
            }else {
                dao.addNewMetering(selectedMeteringDevice, inputReadingValue);

                readingLogs.removeAll(readingLogs);
                readingLogs.addAll(dao.getReadingLog(selectedStreet, selectedMeteringDevice));

                Dialog.successfulInfoWindow("Успех!", "Новое показание прибора добавлено в журнал показаний!");
            }
        }else{
            dao.addNewMetering(selectedMeteringDevice, inputReadingValue);

            readingLogs.removeAll(readingLogs);
            readingLogs.addAll(dao.getReadingLog(selectedStreet, selectedMeteringDevice));

            Dialog.successfulInfoWindow("Успех!", "Новое показание прибора добавлено в журнал показаний!");
        }
    }


    /**
     * Действие для выбранной из выпадающего списка пулицы.
     *
     * После выбора конкретной улицы разблокируется выпадающее меню для выбора ИПУ, а в журнале показаний
     * отобразятся все показания, введенные для всех ИПУ на этой улице.
     */
    private void actionForSelectedStreetsFromComboBox(){
        selectedStreet = streets.getSelectionModel().getSelectedItem();
        meteringDevice.setDisable(false);
        meteringDevice.setItems(FXCollections.observableArrayList(dao.getMeteringDevices(selectedStreet)));

        readingLogs.removeAll(readingLogs);

        readingLogs.addAll(dao.getReadingLog(selectedStreet));
        catalogReading.setItems(readingLogs);
    }

    /**
     * Действие для выбранного из выпадающего меню прибора учета.
     *
     * После выбора конкретного ИПУ разблокируется кнопка внесения показаний, а в журнале показаний
     * отобразятся только показания выбранного ИПУ.
     */
    private void actionForMeteringDeviceFromComboBox(){
        selectedMeteringDevice = meteringDevice.getSelectionModel().getSelectedItem();
        button.setDisable(false);

        readingLogs.removeAll(readingLogs);

        readingLogs.addAll(dao.getReadingLog(selectedStreet, selectedMeteringDevice));
        catalogReading.setItems(readingLogs);
    }

    @FXML
    private void editCatalogStreet(){
        mainApp.showCatalogStreet();
    }

    @FXML
    private void addNewMeteringDevice(){ mainApp.showAddNewMeteringDevice(); }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        streets.setItems(mainApp.getStreets());
    }

    public void setDao(DAO dao){this.dao = dao;}
}
