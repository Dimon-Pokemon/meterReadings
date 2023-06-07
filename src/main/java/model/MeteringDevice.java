package model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MeteringDevice {
    private final StringProperty street;
    private final StringProperty typeMeteringDevice;

    private final LongProperty serialNumber;


    MeteringDevice(String street, String typeMeteringDevice, Long serialNumber){
        this.street = new SimpleStringProperty(street);
        this.typeMeteringDevice = new SimpleStringProperty(typeMeteringDevice);
        this.serialNumber = new SimpleLongProperty(serialNumber);
    }

    MeteringDevice(Street street, TypeMeteringDevice typeMeteringDevice, Long serialNumber){
        this(street.toString(), typeMeteringDevice.toString(), serialNumber);
    }


    public long getSerialNumber() {
        return serialNumber.get();
    }

    public LongProperty getSerialNumberProperty() {
        return serialNumber;
    }

    public void setSerialNumber(Long newSerialNumber){
        this.serialNumber.set(newSerialNumber);
    }

    public String getTypeMeteringDevice() {
        return typeMeteringDevice.get();
    }

    public StringProperty getTypeMeteringDeviceProperty() {
        return typeMeteringDevice;
    }

    public void setTypeMeteringDevice(String newTypeMeteringDevice){
        this.typeMeteringDevice.set(newTypeMeteringDevice);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty getStreetProperty() {
        return street;
    }

    public void setStreet(String newStreet){
        this.street.set(newStreet);
    }
}
