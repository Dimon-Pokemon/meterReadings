package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class TypeMeteringDevice {

    private final StringProperty nameType;
    private final StringProperty facility;
    private final IntegerProperty accuracy;

    TypeMeteringDevice(String nameType, String facility, Integer accuracy){
        this.nameType = new SimpleStringProperty(nameType);
        this.facility = new SimpleStringProperty(facility);
        this.accuracy = new SimpleIntegerProperty(accuracy);
    }

    TypeMeteringDevice(StringProperty nameType, StringProperty facility, IntegerProperty accuracy){
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

    public IntegerProperty getAccuracyProperty() {
        return accuracy;
    }

    public void setAccuracy(Integer newAccuracy){
        this.accuracy.set(newAccuracy);
    }
}
