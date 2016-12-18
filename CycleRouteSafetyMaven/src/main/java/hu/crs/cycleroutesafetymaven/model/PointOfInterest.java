/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.crs.cycleroutesafetymaven.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ahorvath
 */
public class PointOfInterest {
    private IntegerProperty poiId;
    private StringProperty routeName;
    private StringProperty addressText;
    private StringProperty poiType;
    private DoubleProperty Lat;
    private DoubleProperty Lon;
    private BooleanProperty isAlreadyPlanned;
    private StringProperty textContent;
    
    public PointOfInterest() {
    }
    
    public PointOfInterest(String routeName, String poiType, String pointAddressText) {
        this.routeName.setValue(routeName);
        this.poiType.setValue(poiType);
        this.addressText.setValue(pointAddressText);
        this.Lat.setValue(null);                //unnecessary, since DB has default value false
        this.Lon.setValue(null);                //unnecessary, since DB has default value false
        this.isAlreadyPlanned.setValue(false);  //unnecessary, since DB has default value false
        this.textContent.setValue("");
    }
    
    public PointOfInterest(String routeName, String poiType, String addressText, Double lat, Double lon, boolean isAlreadyPlanned, String textContent) {
        this.routeName.setValue(routeName);
        this.poiType.setValue(poiType);
        this.addressText.setValue(addressText);
        this.Lat.setValue(lat);
        this.Lon.setValue(lon);
        this.isAlreadyPlanned.setValue(isAlreadyPlanned);
        this.textContent.setValue(textContent);
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

    public String getAddressText() {
        return addressText.getValue();
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
        return Lat.getValue();
    }

    public void setLat(Double lat) {
        this.Lat.setValue(lat);
    }

    public Double getLon() {
        return Lon.getValue();
    }

    public void setLon(Double lon) {
        this.Lon.setValue(lon);
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


}
