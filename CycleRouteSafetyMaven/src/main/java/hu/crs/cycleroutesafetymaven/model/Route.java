package hu.crs.cycleroutesafetymaven.model;

import java.time.LocalDate;
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
    private final ObjectProperty<LocalDate> lastUpdateTime;
    /**
     * Default constructor.
     */
    public Route() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param name
     * @param author
     */
    public Route(String name, String author) {
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);

        // Some initial dummy data, just for convenient testing.
        this.start = new SimpleStringProperty("some startPlace");
        this.length = new SimpleIntegerProperty(12);
        this.finish = new SimpleStringProperty("some finishPlace");
        this.lastUpdateTime = new SimpleObjectProperty<LocalDate>(LocalDate.of(2016, 11, 18));
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

    public LocalDate getLastUpdateTime() {
        return lastUpdateTime.get();
    }

    public void setLastUpdateTime(LocalDate newUpdateTime) {
        this.lastUpdateTime.set(newUpdateTime);
    }

    public ObjectProperty<LocalDate> lastUpdateTimeProperty() {
        return lastUpdateTime;
    }
}