package model;


import java.time.LocalDate;

import javafx.beans.property.*;

public class ReadingLog {

    private final ObjectProperty<LocalDate> date;
    private final StringProperty nameMeteringDevice;
    private final StringProperty titleTypeMeteringDevice;
    private final DoubleProperty readings;
    private final StringProperty street;
    private final IntegerProperty accuracy;
    public ReadingLog(LocalDate date, String nameMeteringDevice, String titleTypeMeteringDevice, Double readings, String street, Integer accuracy){
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.nameMeteringDevice = new SimpleStringProperty(nameMeteringDevice);
        this.titleTypeMeteringDevice = new SimpleStringProperty(titleTypeMeteringDevice);
        this.readings = new SimpleDoubleProperty(readings);
        this.street = new SimpleStringProperty(street);
        this.accuracy = new SimpleIntegerProperty(accuracy);
    }

    public ReadingLog(ObjectProperty date,
               StringProperty nameMeteringDevice,
               StringProperty titleTypeMeteringDevice,
               DoubleProperty readings,
               StringProperty street,
               IntegerProperty accuracy)
    {
        this((LocalDate) date.get(), nameMeteringDevice.get(), titleTypeMeteringDevice.get(), readings.get(), street.get(), accuracy.get());
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> getDateProperty() {
        return date;
    }

    public void setDate(LocalDate date){
        this.date.set(date);
    }

    public String getNameMeteringDevice() {
        return nameMeteringDevice.get();
    }

    public StringProperty getNameMeteringDeviceProperty() {
        return nameMeteringDevice;
    }

    public void setNameMeteringDevice(String nameMeteringDevice){
        this.nameMeteringDevice.set(nameMeteringDevice);
    }

    public String getTitleTypeMeteringDevice() {
        return titleTypeMeteringDevice.get();
    }

    public StringProperty getTitleTypeMeteringDeviceProperty() {
        return titleTypeMeteringDevice;
    }

    public void setTitleTypeMeteringDevice(String titleTypeMeteringDevice){
        this.titleTypeMeteringDevice.set(titleTypeMeteringDevice);
    }

    public double getReadings() {
        return readings.get();
    }

    public DoubleProperty getReadingsProperty() {
        return readings;
    }

    public void setReadings(Double readings){
        this.readings.set(readings);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty getStreetProperty() {
        return street;
    }

    public void setStreet(String street){
        this.street.set(street);
    }

    public int getAccuracy() {
        return accuracy.get();
    }

    public IntegerProperty getAccuracyProperty() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy){
        this.accuracy.set(accuracy);
    }
}
