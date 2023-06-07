package model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.LongProperty;

public class TypeMeteringDevice {

    private final StringProperty street;
    private final StringProperty typeMeteringDevice;

    private final LongProperty serialNumber;


    TypeMeteringDevice(String street, String typeMeteringDevice, Long serialNumber){
        this.street = new SimpleStringProperty(street);
        this.typeMeteringDevice = new SimpleStringProperty(typeMeteringDevice);
        this.serialNumber = new SimpleLongProperty(serialNumber);
    }

    TypeMeteringDevice(Street street, TypeMeteringDevice typeMeteringDevice, Long serialNumber){
        this(street.toString(), typeMeteringDevice.toString(), serialNumber);
    }


    public long getSerialNumber() {
        return serialNumber.get();
    }

    public LongProperty serialNumberProperty() {
        return serialNumber;
    }

    public void setSerialNumberProperty(Long serialNumber){
        this.serialNumber.set(serialNumber);
    }

    public String getTypeMeteringDevice() {
        return typeMeteringDevice.get();
    }

    public StringProperty typeMeteringDeviceProperty() {
        return typeMeteringDevice;
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }
}
