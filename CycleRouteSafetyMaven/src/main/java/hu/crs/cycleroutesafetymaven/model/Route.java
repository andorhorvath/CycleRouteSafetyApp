package hu.crs.cycleroutesafetymaven.model;

import com.lynden.gmapsfx.javascript.object.LatLong;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Route.
 *
 * @author Andor Horváth
 */
public class Route {

    private final StringProperty name;  //uniq
    private final StringProperty author;
    private final PointOfInterest start;
    private final PointOfInterest finish;
    private final IntegerProperty length;
    private final ObjectProperty<Date> lastUpdateTime;
    private final BooleanProperty isAlreadyPlanned;
    
    


    /**
     * Default constructor.
     */
    public Route() {
        this("", "", "", "", 0, null, false);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param name
     * @param author
     * @param startAddress
     * @param finishAddress
     * @param length
     * @param lastUpdateTime
     * @param isAlreadyPlanned
     */
    public Route(String name, String author, String startAddress, String finishAddress, Integer length, Date lastUpdateTime, Boolean isAlreadyPlanned) {
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.start = new PointOfInterest(name, "start", startAddress);
        this.finish = new PointOfInterest(name, "finish", finishAddress);
        
        this.length = new SimpleIntegerProperty(length);
        this.lastUpdateTime = new SimpleObjectProperty<>(lastUpdateTime);
        this.isAlreadyPlanned = new SimpleBooleanProperty(isAlreadyPlanned);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String firstName) {
        this.name.set(firstName);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String lastName) {
        this.author.set(lastName);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public PointOfInterest getStart() {
        return start;
    }

    public void setStart(PointOfInterest newStart) {
        //ha akarom majd figyelni, akkor ezeket cserélni kell a Property-kre
        this.finish.setAddressText(newStart.getAddressText());
        this.finish.setLat(newStart.getLat());
        this.finish.setLon(newStart.getLon());
        this.finish.setPoiId(newStart.getPoiId());
        this.finish.setPoiType(newStart.getPoiType());
        this.finish.setRouteName(newStart.getRouteName());
        this.finish.setTextContent(newStart.getTextContent());
    }

    public StringProperty getStartProperty() {
        return this.start.addressTextProperty();
    }

    public int getLength() {
        return length.get();
    }

    public void setLength(int newLength) {
        this.length.set(newLength);
    }

    public IntegerProperty lengthProperty() {
        return length;
    }

    public PointOfInterest getFinish() {
        return finish;
    }

    public void setFinish(PointOfInterest newFinish) {
        //ha akarom majd figyelni, akkor ezeket cserélni kell a Property-kre
        this.finish.setAddressText(newFinish.getAddressText());
        this.finish.setLat(newFinish.getLat());
        this.finish.setLon(newFinish.getLon());
        this.finish.setPoiId(newFinish.getPoiId());
        this.finish.setPoiType(newFinish.getPoiType());
        this.finish.setRouteName(newFinish.getRouteName());
        this.finish.setTextContent(newFinish.getTextContent());
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime.get();
    }

    public void setLastUpdateTime(Date newUpdateTime) {
        this.lastUpdateTime.set(newUpdateTime);
    }

    public ObjectProperty<Date> lastUpdateTimeProperty() {
        return lastUpdateTime;
    }
    
    public void setIsDirectionsUsed(Boolean newIsDirectionsUsed) {
        this.isAlreadyPlanned.set(newIsDirectionsUsed);
    }
    
    public BooleanProperty getIsDirectionsUsed() {
        return isAlreadyPlanned;
    }
}