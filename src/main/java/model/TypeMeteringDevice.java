package model;

import javafx.beans.property.*;

public class TypeMeteringDevice {

    private final LongProperty id;
    private final StringProperty titleType;
    private final StringProperty facility;
    private final IntegerProperty capacity;

    public TypeMeteringDevice(Long id, String titleType, String facility, Integer capacity){
        this.id = new SimpleLongProperty(id);
        this.titleType = new SimpleStringProperty(titleType);
        this.facility = new SimpleStringProperty(facility);
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public TypeMeteringDevice(LongProperty id, StringProperty titleType, StringProperty facility, IntegerProperty capacity){
        this(id.get(), titleType.get(), facility.get(), capacity.get());
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

    public double getCapacity() {
        return capacity.get();
    }

    public IntegerProperty getCapacityProperty() {
        return capacity;
    }

    public void setCapacity(Integer newAccuracy){
        this.capacity.set(newAccuracy);
    }
}
