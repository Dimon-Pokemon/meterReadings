package model;

import javafx.beans.property.*;

public class TypeMeteringDevice {

    private final LongProperty id;
    private final StringProperty nameType;
    private final StringProperty facility;
    private final IntegerProperty accuracy;

    TypeMeteringDevice(Long id, String nameType, String facility, Integer accuracy){
        this.id = new SimpleLongProperty(id);
        this.nameType = new SimpleStringProperty(nameType);
        this.facility = new SimpleStringProperty(facility);
        this.accuracy = new SimpleIntegerProperty(accuracy);
    }

    TypeMeteringDevice(LongProperty id, StringProperty nameType, StringProperty facility, IntegerProperty accuracy){
        this(id.get(), nameType.get(), facility.get(), accuracy.get());
    }

    @Override
    public String toString() {
        return nameType.get();
    }


    public long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public void setId(Long newId){
        this.id.set(newId);
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
