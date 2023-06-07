package model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Street {

    private final StringProperty region;
    private final StringProperty city;
    private final StringProperty streetName;

    Street(String region, String city, String streetName){
        this.region = new SimpleStringProperty(region);
        this.city = new SimpleStringProperty(city);
        this.streetName = new SimpleStringProperty(streetName);
    }

    public String getRegion() {
        return region.get();
    }

    public StringProperty getRegionProperty() {
        return region;
    }

    public void setRegion(String newRegion){
        this.region.set(newRegion);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty getCityProperty() {
        return city;
    }

    public void setCity(String newCity){
        this.city.set(newCity);
    }

    public String getStreetName() {
        return streetName.get();
    }

    public StringProperty getStreetNameProperty() {
        return streetName;
    }

    public void setStreetName(String newStreetName){
        this.streetName.set(newStreetName);
    }
}
