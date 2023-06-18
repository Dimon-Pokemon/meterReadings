package model;

import javafx.beans.property.*;

public class TypeMeteringDevice {

    private final LongProperty id;
    private final StringProperty titleType;
    private final StringProperty facility;
    private final IntegerProperty capacity;
    private final IntegerProperty accuracy;

    public TypeMeteringDevice(Long id, String titleType, String facility, Integer capacity, Integer accuracy){
        this.id = new SimpleLongProperty(id);
        this.titleType = new SimpleStringProperty(titleType);
        this.facility = new SimpleStringProperty(facility);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.accuracy = new SimpleIntegerProperty(accuracy);
    }

    public TypeMeteringDevice(LongProperty id, StringProperty titleType, StringProperty facility, IntegerProperty capacity, IntegerProperty accuracy){
        this(id.get(), titleType.get(), facility.get(), capacity.get(), accuracy.get());
    }

    @Override
    public String toString() {
        return "%s (Facility: %s; Capacity: %d)".formatted(titleType.get(), facility.get(), capacity.get());
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
    public String getTitleType() {
        return titleType.get();
    }

    public StringProperty getTitleTypeProperty() {
        return titleType;
    }

    public void setTitleType(String newType){
        this.titleType.set(newType);
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

    public Integer getCapacity() {
        return capacity.get();
    }

    public IntegerProperty getCapacityProperty() {
        return capacity;
    }

    public void setCapacity(Integer newAccuracy){
        this.capacity.set(newAccuracy);
    }

    public Integer getAccuracy() {
        return accuracy.get();
    }

    public IntegerProperty getAccuracyProperty() {
        return accuracy;
    }

    public void setAccuracy(Integer newValue){this.accuracy.set(newValue);}
}
