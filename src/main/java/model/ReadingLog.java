package model;


import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.*;

public class ReadingLog {

    private final ObjectProperty<Date> date;
    private final StringProperty serialNumberMeteringDevice;
    private final StringProperty titleTypeMeteringDevice;
    private final DoubleProperty readings;
    private final StringProperty street;
    private final IntegerProperty capacity;


    public ReadingLog(Date date, String serialNumberMeteringDevice, String titleTypeMeteringDevice, Double readings, String street, Integer capacity){
        this.date = new SimpleObjectProperty<Date>(date);
        this.serialNumberMeteringDevice = new SimpleStringProperty(serialNumberMeteringDevice);
        this.titleTypeMeteringDevice = new SimpleStringProperty(titleTypeMeteringDevice);
        this.readings = new SimpleDoubleProperty(readings);
        this.street = new SimpleStringProperty(street);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public ReadingLog(
            ObjectProperty date,
            StringProperty serialNumberMeteringDevice,
            StringProperty titleTypeMeteringDevice,
            DoubleProperty readings,
            StringProperty street,
            IntegerProperty capacity)
    {
        this((Date) date.get(), serialNumberMeteringDevice.get(), titleTypeMeteringDevice.get(), readings.get(), street.get(), capacity.get());
    }

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> getDateProperty() {
        return date;
    }

    public void setDate(Date date){
        this.date.set(date);
    }

    public String getSerialNumberMeteringDevice() {
        return serialNumberMeteringDevice.get();
    }

    public StringProperty getSerialNumberMeteringDeviceProperty() {
        return serialNumberMeteringDevice;
    }

    public void setSerialNumberMeteringDevice(String serialNumberMeteringDevice){
        this.serialNumberMeteringDevice.set(serialNumberMeteringDevice);
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

    public int getCapacity() {
        return capacity.get();
    }

    public IntegerProperty getCapacityProperty() {
        return capacity;
    }

    public void setCapacity(Integer capacity){
        this.capacity.set(capacity);
    }
}
