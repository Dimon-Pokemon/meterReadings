package model;

import javafx.beans.property.*;

public class MeteringDevice {
    private final IntegerProperty id;
    private final StringProperty street;
    private final StringProperty typeMeteringDevice;
    private final LongProperty serialNumber;
    private final IntegerProperty capacity;
    private final IntegerProperty accuracy;


    public MeteringDevice(Integer id, String street, String typeMeteringDevice, Long serialNumber, Integer capacity, Integer accuracy){
        this.id = new SimpleIntegerProperty(id);
        this.street = new SimpleStringProperty(street);
        this.typeMeteringDevice = new SimpleStringProperty(typeMeteringDevice);
        this.serialNumber = new SimpleLongProperty(serialNumber);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.accuracy = new SimpleIntegerProperty(accuracy);
    }

    public MeteringDevice(Integer id, Street street, TypeMeteringDevice typeMeteringDevice, Long serialNumber, Integer capacity, Integer accuracy){
        this(id, street.toString(), typeMeteringDevice.toString(), serialNumber, capacity, accuracy);
    }


    @Override
    public String toString(){
        return "%d".formatted(serialNumber.get());
    }

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public void setId(Integer newValue){
        this.id.set(newValue);
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

    public Integer getCapacity() {
        return capacity.get();
    }

    public IntegerProperty getCapacityProperty() {
        return capacity;
    }

    public void setCapacity(Integer newValue){
        this.capacity.set(newValue);
    }

    public int getAccuracy() {
        return accuracy.get();
    }

    public IntegerProperty getAccuracyProperty() {
        return accuracy;
    }

    public void setAccuracy(Integer newValue){this.accuracy.set(newValue);}

}
