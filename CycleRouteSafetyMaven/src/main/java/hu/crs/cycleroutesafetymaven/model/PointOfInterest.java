/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.crs.cycleroutesafetymaven.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ahorvath
 */
public class PointOfInterest {
    private final IntegerProperty poiId;    // we will see whether I need this or not at all
    private final StringProperty routeName;
    private final StringProperty addressText;
    private final StringProperty poiType;
    private final DoubleProperty lat;
    private final DoubleProperty lon;
    private final BooleanProperty isAlreadyPlanned;
    private final StringProperty textContent;
    
    public PointOfInterest() {
        this(null, null, null);
    }
    
    public PointOfInterest(String routeName, String poiType, String addressText) {
        this.poiId = new SimpleIntegerProperty();
        this.routeName = new SimpleStringProperty(routeName);
        this.poiType = new SimpleStringProperty(poiType);
        this.addressText = new SimpleStringProperty(addressText);
        this.lat = new SimpleDoubleProperty();                //unnecessary, since DB has default value false
        this.lon = new SimpleDoubleProperty();                //unnecessary, since DB has default value false
        this.isAlreadyPlanned = new SimpleBooleanProperty(false);  //unnecessary, since DB has default value false
        this.textContent = new SimpleStringProperty();
    }
    
    public PointOfInterest(String routeName, String poiType, String addressText, Double lat, Double lon, Boolean isAlreadyPlanned, String textContent) {
        this.poiId = new SimpleIntegerProperty();
        this.routeName = new SimpleStringProperty(routeName);
        this.poiType = new SimpleStringProperty(poiType);
        this.addressText = new SimpleStringProperty(addressText);
        this.lat = new SimpleDoubleProperty(lat);                //unnecessary, since DB has default value false
        this.lon = new SimpleDoubleProperty(lon);                //unnecessary, since DB has default value false
        this.isAlreadyPlanned = new SimpleBooleanProperty(isAlreadyPlanned);  //unnecessary, since DB has default value false
        this.textContent = new SimpleStringProperty(textContent);
    }

    public Integer getPoiId() {
        return poiId.getValue();
    }

    public void setPoiId(Integer poiId) {
        this.poiId.set(poiId);
    }

    public String getRouteName() {
        return routeName.getValue();
    }

    public void setRouteName(String routeName) {
        this.routeName.setValue(routeName);
    }

    public final String getAddressText() {
        return addressText.get();
    }

    public void setAddressText(String addressText) {
        this.addressText.setValue(addressText);
    }
    
    public String getPoiType() {
        return poiType.getValue();
    }

    public void setPoiType(String poiType) {
        this.poiType.setValue(poiType);
    }

    public Double getLat() {
        return lat.getValue();
    }

    public void setLat(Double lat) {
        this.lat.setValue(lat);
    }

    public Double getLon() {
        return lon.getValue();
    }

    public void setLon(Double lon) {
        this.lon.setValue(lon);
    }

    public Boolean getIsAlreadyPlanned() {
        return isAlreadyPlanned.getValue();
    }

    public void setIsAlreadyPlanned(Boolean isAlreadyPlanned) {
        this.isAlreadyPlanned.setValue(isAlreadyPlanned);
    }

    public String getTextContent() {
        return textContent.getValue();
    }

    public void setTextContent(String textContent) {
        this.textContent.setValue(textContent);
    }

    public IntegerProperty poiId() {
        return poiId;
    }
    
    public StringProperty addressTextProperty() {
        return addressText;
    }

    public StringProperty routeName() {
        return routeName;
    }
    
    public StringProperty poiType() {
        return poiType;
    }
    
    public DoubleProperty lat() {
        return lat;
    }
    
    public DoubleProperty lon() {
        return lon;
    }
    
    public BooleanProperty isAlreadyPlanned() {
        return isAlreadyPlanned;
    }
    
    public StringProperty textContent() {
        return textContent;
    }
}
