package hu.crs.cycleroutesafetymaven.model;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
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

    private final StringProperty name;
    private final StringProperty author;
    private final StringProperty start;
    private final StringProperty finish;
    private final IntegerProperty length;
    private final ObjectProperty<Date> lastUpdateTime;
    private final Boolean isDirectionsUsed;
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
     * @param start
     * @param finish
     * @param length
     * @param lastUpdateTime
     */
    public Route(String name, String author, String start, String finish, Integer length, Date lastUpdateTime, Boolean isDirectionsUsed) {
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.start = new SimpleStringProperty(start);
        this.finish = new SimpleStringProperty(finish);
        
        this.length = new SimpleIntegerProperty(length);
        this.lastUpdateTime = new SimpleObjectProperty<>(lastUpdateTime);
        this.isDirectionsUsed = isDirectionsUsed;
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

    public String getStart() {
        return start.get();
    }

    public void setStart(String street) {
        this.start.set(street);
    }

    public StringProperty startProperty() {
        return start;
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

    public String getFinish() {
        return finish.get();
    }

    public void setFinish(String newFinish) {
        this.finish.set(newFinish);
    }

    public StringProperty finishProperty() {
        return finish;
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
}