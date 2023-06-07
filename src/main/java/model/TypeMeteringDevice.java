package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;

public class TypeMeteringDevice {

    private final StringProperty nameType;
    private final StringProperty facility;

    private final DoubleProperty accuracy;

    TypeMeteringDevice(String nameType, String facility, Double accuracy){
        this.nameType = new SimpleStringProperty(nameType);
        this.facility = new SimpleStringProperty(facility);
        this.accuracy = new SimpleDoubleProperty(accuracy);
    }

    TypeMeteringDevice(StringProperty nameType, StringProperty facility, DoubleProperty accuracy){
        this(nameType.get(), facility.get(), accuracy.get());
    }


    public String getNameType() {
        return nameType.get();
    }

    public StringProperty getNameTypeProperty() {
        return nameType;
    }

    public void setNameType(String newType){
        this.nameType.set(newType);
    }

    public String getFacility() {
        return facility.get();
    }

    public void setFacility(String newFacility){
        this.facility.set(newFacility);
    }

    public StringProperty getFacilityProperty() {
        return facility;
    }

    public double getAccuracy() {
        return accuracy.get();
    }

    public DoubleProperty getAccuracyProperty() {
        return accuracy;
    }

    public void setAccuracy(Double newAccuracy){
        this.accuracy.set(newAccuracy);
    }
}
